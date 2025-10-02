package com.niupleis.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View

import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)

        setContentView(R.layout.activity_main)

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
    }
}