package com.niupleis.webview

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Build
import android.view.View

import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted: Boolean ->
            if (isGranted) {
                webView.evaluateJavascript(
                    "if(typeof onNotificationPermissionGranted === 'function') {}",
                    null
                )
            } else {
                webView.evaluateJavascript(
                    "if(typeof onNotificationPermissionDenied === 'function') {}",
                    null
                )
            }
        }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)

        setContentView(R.layout.activity_main)

        createNotificationChannel(this)

        webView = findViewById<WebView>(R.id.webview)

        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true

            settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
            settings.mediaPlaybackRequiresUserGesture = false

            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            webViewClient = WebViewClient()
            loadUrl("file:///android_asset/index.html")
        }
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        hideSystemBars()
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                webView.evaluateJavascript(
                    "if(typeof onBackPressed === 'function') { onBackPressed(); }",
                    null
                )
            }
        })
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "game_notifications"
            val channelName = "Game Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun hideSystemBars() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    inner class WebAppInterface(private val mContext: MainActivity) {
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun closeGame() {
            finish()
        }

        @JavascriptInterface
        fun requestNotifications() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        @JavascriptInterface
        fun notification(title: String, content: String) {
            val builder = NotificationCompat.Builder(mContext, "game_notifications")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_LOW)
            with(NotificationManagerCompat.from(mContext)) {
                if (ActivityCompat.checkSelfPermission(
                        mContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(1, builder.build())
            }
        }

        @JavascriptInterface
        fun fileSet(filename: String, content: String): Boolean {
            return try {
                val file = File(filesDir, filename)
                file.writeText(content)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        @JavascriptInterface
        fun fileGet(filename: String): String? {
            return try {
                val file = File(filesDir, filename)
                if (file.exists()) {
                    file.readText()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        @JavascriptInterface
        fun fileDel(filename: String): Boolean {
            return try {
                val file = File(filesDir, filename)
                if (file.exists()) {
                    file.delete()
                } else {
                    false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        @JavascriptInterface
        fun fileExists(filename: String): Boolean {
            return try {
                val file = File(filesDir, filename)
                file.exists()
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        @JavascriptInterface
        fun fileSize(filename: String): Long {
            return try {
                val file = File(filesDir, filename)
                if (file.exists()) {
                    file.length()
                } else {
                    -1L
                }
            } catch (e: Exception) {
                e.printStackTrace()
                -1L
            }
        }

        @JavascriptInterface
        fun fileList(): String {
            return try {
                val files = filesDir.listFiles()
                if (files != null && files.isNotEmpty()) {
                    files.joinToString(",") { it.name }
                } else {
                    ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }
}