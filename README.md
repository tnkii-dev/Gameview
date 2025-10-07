# Gameview

<details><summary>Español</summary>
Plantilla de Android Studio para juegos hechos con Javascript.

## Info:

Esta plantilla te permite convertir tu juego HTML5 en una aplicación Android de manera sencilla.
Originalmente creada para compilar juegos creados con el motor de juegos de navegador [**Nekoplay**](https://nekoplay.niupeis.com/wiki/)

## Pendientes:

- [x] Webview
- [x] Cerrar desde js
- [x] Boton de volver
- [x] Toasts
- [x] Pantalla completa "edge to edge"
- [x] Notificaciónes (Beta)
- [x] Almacenamiento
- [ ] Accesos directos
- [ ] Interfáz de Anuncios (¿?)

## Tutorial: Uso de la Plantilla Android Studio

### 1. Cambiar el nombre del paquete

Por defecto el nombre de paquete es `com.niupleis.webview`, deverías cambiarlo a tu nombre de dominio y app

1. En Android Studio, haz clic derecho sobre `com.niupleis.webview`
2. Selecciona **Refactor → Rename**
3. Cambia el nombre al que desees (por ejemplo: `com.tuempresa.tujuego`)
4. Actualiza el `applicationId` en `app/build.gradle.kts`:

```gradle
android {
    namespace = "com.niupleis.webview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.niupleis.webview"
        ---
    }
    ---
}
```

### 2. Agregar tu juego

Coloca todos los archivos de tu juego en `app/src/main/assets/`.

**Importante:** El archivo principal debe llamarse `index.html` y estar en la raíz de `assets/`.

Ejemplo de estructura:
```
app/src/main/assets/
├── index.html
├── styles.css
└── game.js
```

### 3. Estilo de la app

#### Fondo

El color de fondo de la applicación dependerá del tema del usuario: color blanco en tema claro y color negro en tema oscuro. Es más facil si cambias esto con css.

#### Orientación

En el archivo `app/src/main/AndroidManifest.xml` cambia la orientación de la pantalla en `manifest > application > activity : android:screenOrientation`.
```
android:screenOrientation="sensor"
tools:ignore="LockedOrientationActivity">
<!-- landscape|portrait|sensorLandscape|sensorPortrait|sensor|unspecified -->
```

#### Íconos

Puedes crear íconos para tu app desde Android Studio siguendo [esta guía](https://developer.android.com/studio/write/create-app-icons).
O puedes generar mipmaps a partir de una imágen con cualquier herramienta online como [appicon.co](https://www.appicon.co/) e importalos a `app/src/main/res/?`.

### 4. Interfaz JavaScript con Android

La plantilla proporciona una interfaz JavaScript para interactuar con funcionalidades nativas de Android.

#### Funciones disponibles:

**Mostrar Toast (mensaje emergente):**
```javascript
Android.showToast("¡Hola desde JavaScript!");
```

**Cerrar la aplicación:**
```javascript
Android.closeGame();
```

**Notificaciones**
```javascript
Android.requestNotifications() // Pedir permiso de notificaciones
Android.notification("Title", "Content") // Crear una notificación básica
```

**Almacenamiento**
```javascript
Android.fileSet('filename', 'content') // Guardar archivo
Android.fileGet('filename') // Leer un archivo
Android.fileDel('filename') // Eliminar un archivo
Android.fileExist('filename') // Comprobar si un archivo existe
Android.fileSize('filename') // Obtener el tamaño del archivo
Android.fileList() // Lista de todos los archivos
```

#### Manejar el botón de retroceso

Define la función `onBackPressed` en tu código JavaScript para controlar qué sucede cuando el usuario presiona el botón de retroceso:

```javascript
function onBackPressed() {
    // Tu lógica aquí
    if (confirm("¿Estás seguro de que quieres salir?")) {
        Android.closeGame();
    }
}
```

## Listo

Ahora puedes ejecutar tu aplicación en Android Studio y probar tu juego en un dispositivo o emulador.

---

**Nota:** Recuerda probar las funciones de la interfaz JavaScript en un dispositivo Android real o emulador, ya que no funcionarán en un navegador web normal.
</details>

<details><summary>English</summary>
Android Studio Template for Games Made with Javascript.

## Info:

This template allows you to easily convert your HTML5 game into an Android application.
Originally created to compile games made with the [**Nekoplay**](https://nekoplay.niupeis.com/wiki/) browser game engine.

## To-Do:

- [x] Webview
- [x] Close from js
- [x] Back button
- [x] Toasts
- [x] Fullscreen "edge to edge"
- [x] Notifications (Beta)
- [x] Storage
- [ ] Shortcuts
- [ ] Ads Interface (?)

## Tutorial: Using the Android Studio Template

### 1. Change the Package Name

By default, the package name is `com.niupleis.webview`; you should change it to your domain and app name.

1. In Android Studio, right-click on `com.niupleis.webview`
2. Select **Refactor → Rename**
3. Change the name to your desired one (e.g., `com.yourcompany.yourgame`)
4. Update the `applicationId` in `app/build.gradle.kts`:

```gradle
android {
    namespace = "com.niupleis.webview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.niupleis.webview"
        ---
    }
    ---
}
```

### 2. Add Your Game

Place all your game files in `app/src/main/assets/`.

**Important:** The main file must be named `index.html` and be located in the root of `assets/`.

Example structure:
```
app/src/main/assets/
├── index.html
├── styles.css
└── game.js
```

### 3. App Styling

#### Background

The application's background color will depend on the user's theme: white in light theme and black in dark theme. It's easier to change this with CSS.

#### Orientation

In the `app/src/main/AndroidManifest.xml` file, change the screen orientation in `manifest > application > activity : android:screenOrientation`.
```
android:screenOrientation="sensor"
tools:ignore="LockedOrientationActivity">
<!-- landscape|portrait|sensorLandscape|sensorPortrait|sensor|unspecified -->
```

#### Icons

You can create icons for your app from Android Studio by following [this guide](https://developer.android.com/studio/write/create-app-icons).
Or you can generate mipmaps from an image using any online tool like [appicon.co](https://www.appicon.co/) and import them into `app/src/main/res/?`.

### 4. JavaScript Interface with Android

The template provides a JavaScript interface to interact with native Android functionalities.

#### Available Functions:

**Show Toast (pop-up message):**
```javascript
Android.showToast("Hello from JavaScript!");
```

**Close the application:**
```javascript
Android.closeGame();
```

**Notifications**
```javascript
Android.requestNotifications() // Request notification permission
Android.notification("Title", "Content") // Create a basic notification
```

**Storage**
```javascript
Android.fileSet('filename', 'content') // Save file
Android.fileGet('filename') // Read a file
Android.fileDel('filename') // Delete a file
Android.fileExist('filename') // Check if a file exists
Android.fileSize('filename') // Get file size
Android.fileList() // List all files
```

#### Handle the Back Button

Define the `onBackPressed` function in your JavaScript code to control what happens when the user presses the back button:

```javascript
function onBackPressed() {
    // Your logic here
    if (confirm("Are you sure you want to exit?")) {
        Android.closeGame();
    }
}
```

## Done

You can now run your application in Android Studio and test your game on a device or emulator.

---

**Note:** Remember to test the JavaScript interface functions on a real Android device or emulator, as they will not work in a normal web browser.
</details>

<details><summary>日本語</summary>
Android Studio テンプレート：Javascript で作成されたゲーム用

## 情報:

このテンプレートを使用すると、HTML5ゲームを簡単にAndroidアプリに変換できます。
元々はブラウザゲームエンジン [**Nekoplay**](https://nekoplay.niupeis.com/wiki/) で作成されたゲームをビルドするために作成されました。

## 未実装 / 今後の課題:

- [x] Webview
- [x] JSからの終了
- [x] 戻るボタン
- [x] トースト
- [x] 全画面表示 "edge to edge"
- [x] 通知 (Beta)
- [x] ストレージ
- [ ] ショートカット
- [ ] 広告インターフェース (?)

## チュートリアル: Android Studio テンプレートの使用方法

### 1. パッケージ名の変更

デフォルトのパッケージ名は `com.niupleis.webview` です。ご自身のドメインとアプリ名に変更する必要があります。

1. Android Studio で `com.niupleis.webview` を右クリックします。
2. **Refactor → Rename** を選択します。
3. 任意の名前に変更します (例: `com.あなたの企業.あなたのゲーム`)
4. `app/build.gradle.kts` の `applicationId` を更新します:

```gradle
android {
    namespace = "com.niupleis.webview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.niupleis.webview"
        ---
    }
    ---
}
```

### 2. ゲームの追加

ゲームのすべてのファイルを `app/src/main/assets/` に配置します。

**重要:** メインファイルは `index.html` という名前にし、`assets/` のルートディレクトリに置く必要があります。

ディレクトリ構造の例:
```
app/src/main/assets/
├── index.html
├── styles.css
└── game.js
```

### 3. アプリのスタイル

#### 背景

アプリケーションの背景色はユーザーのテーマに依存します：ライトテーマでは白、ダークテーマでは黒です。CSSでこれを変更する方が簡単です。

#### 画面の向き

`app/src/main/AndroidManifest.xml` ファイルの `manifest > application > activity : android:screenOrientation` で画面の向きを変更します。
```
android:screenOrientation="sensor"
tools:ignore="LockedOrientationActivity">
<!-- landscape|portrait|sensorLandscape|sensorPortrait|sensor|unspecified -->
```

#### アイコン

[このガイド](https://developer.android.com/studio/write/create-app-icons) に従って Android Studio からアプリのアイコンを作成できます。
または、[appicon.co](https://www.appicon.co/) などのオンラインツールを使用して画像から mipmap を生成し、`app/src/main/res/?` にインポートできます。

### 4. JavaScript と Android のインターフェース

このテンプレートは、Android のネイティブ機能と対話するための JavaScript インターフェースを提供します。

#### 利用可能な関数:

**トースト（ポップアップメッセージ）を表示:**
```javascript
Android.showToast("JavaScript からこんにちは！");
```

**アプリケーションを終了:**
```javascript
Android.closeGame();
```

**通知**
```javascript
Android.requestNotifications() // 通知の許可をリクエスト
Android.notification("タイトル", "内容") // 基本通知を作成
```

**ストレージ**
```javascript
Android.fileSet('filename', 'content') // ファイルを保存
Android.fileGet('filename') // ファイルを読み取り
Android.fileDel('filename') // ファイルを削除
Android.fileExist('filename') // ファイルの存在を確認
Android.fileSize('filename') // ファイルサイズを取得
Android.fileList() // すべてのファイルを一覧表示
```

#### 戻るボタンの処理

ユーザーが戻るボタンを押したときの動作を制御するために、JavaScript コード内で `onBackPressed` 関数を定義します:

```javascript
function onBackPressed() {
    // ここにロジックを記述
    if (confirm("本当に終了しますか？")) {
        Android.closeGame();
    }
}
```

## 完了

これで Android Studio でアプリケーションを実行し、実機またはエミュレーターでゲームをテストできるようになりました。

---

**注意:** JavaScript インターフェースの機能は、通常のウェブブラウザでは動作しないため、Android 実機またはエミュレーターでテストすることを忘れないでください。
</details>

<details><summary>한국어</summary>
Android Studio 템플릿: JavaScript로 만든 게임용

## 정보:

이 템플릿을 사용하면 HTML5 게임을 간단하게 Android 앱으로 변환할 수 있습니다.
원래는 브라우저 게임 엔진 [**Nekoplay**](https://nekoplay.niupeis.com/wiki/)로 생성된 게임을 빌드하기 위해 만들어졌습니다.

## 구현 예정:

- [x] Webview
- [x] JS에서 종료
- [x] 뒤로 가기 버튼
- [x] 토스트 메시지
- [x] 전체 화면 "edge to edge"
- [x] 알림 (Beta)
- [x] 저장소
- [ ] 바로가기
- [ ] 광고 인터페이스 (?)

## 튜토리얼: Android Studio 템플릿 사용법

### 1. 패키지 이름 변경

기본 패키지 이름은 `com.niupleis.webview`입니다. 자신의 도메인과 앱 이름으로 변경해야 합니다.

1. Android Studio에서 `com.niupleis.webview`를 우클릭합니다.
2. **Refactor → Rename**을 선택합니다.
3. 원하는 이름으로 변경합니다 (예: `com.회사명.게임명`)
4. `app/build.gradle.kts`에서 `applicationId`를 업데이트합니다:

```gradle
android {
    namespace = "com.niupleis.webview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.niupleis.webview"
        ---
    }
    ---
}
```

### 2. 게임 추가

게임의 모든 파일을 `app/src/main/assets/`에 넣습니다.

**중요:** 메인 파일은 `index.html`이어야 하며 `assets/`의 루트에 위치해야 합니다.

디렉토리 구조 예시:
```
app/src/main/assets/
├── index.html
├── styles.css
└── game.js
```

### 3. 앱 스타일

#### 배경

앱의 배경 색상은 사용자의 테마에 따라 달라집니다: 라이트 테마에서는 흰색, 다크 테마에서는 검은색입니다. CSS로 이를 변경하는 것이 더 쉽습니다.

#### 화면 방향

`app/src/main/AndroidManifest.xml` 파일의 `manifest > application > activity : android:screenOrientation`에서 화면 방향을 변경합니다.
```
android:screenOrientation="sensor"
tools:ignore="LockedOrientationActivity">
<!-- landscape|portrait|sensorLandscape|sensorPortrait|sensor|unspecified -->
```

#### 아이콘

[이 가이드](https://developer.android.com/studio/write/create-app-icons)를 따라 Android Studio에서 앱 아이콘을 만들 수 있습니다.
또는 [appicon.co](https://www.appicon.co/)와 같은 온라인 도구를 사용하여 이미지에서 mipmap을 생성하고 `app/src/main/res/?`로 가져올 수 있습니다.

### 4. JavaScript와 Android 인터페이스

이 템플릿은 Android 네이티브 기능과 상호작용하기 위한 JavaScript 인터페이스를 제공합니다.

#### 사용 가능한 함수:

**토스트(팝업 메시지) 표시:**
```javascript
Android.showToast("JavaScript에서 안녕하세요!");
```

**애플리케이션 종료:**
```javascript
Android.closeGame();
```

**알림**
```javascript
Android.requestNotifications() // 알림 권한 요청
Android.notification("제목", "내용") // 기본 알림 생성
```

**저장소**
```javascript
Android.fileSet('filename', 'content') // 파일 저장
Android.fileGet('filename') // 파일 읽기
Android.fileDel('filename') // 파일 삭제
Android.fileExist('filename') // 파일 존재 여부 확인
Android.fileSize('filename') // 파일 크기 가져오기
Android.fileList() // 모든 파일 목록
```

#### 뒤로 가기 버튼 처리

사용자가 뒤로 가기 버튼을 눌렀을 때 발생하는 상황을 제어하려면 JavaScript 코드에서 `onBackPressed` 함수를 정의하세요:

```javascript
function onBackPressed() {
    // 여기에 로직을 작성
    if (confirm("정말로 종료하시겠습니까?")) {
        Android.closeGame();
    }
}
```

## 완료

이제 Android Studio에서 애플리케이션을 실행하고 실제 기기 또는 에뮬레이터에서 게임을 테스트할 수 있습니다.

---

**참고:** JavaScript 인터페이스 기능은 일반 웹 브라우저에서는 작동하지 않으므로 Android 실제 기기 또는 에뮬레이터에서 테스트하는 것을 잊지 마세요.
</details>

<details><summary>中文</summary>
Android Studio 模板：用于 JavaScript 制作的游戏

## 信息：

此模板可让您轻松将 HTML5 游戏转换为 Android 应用程序。
最初是为编译使用浏览器游戏引擎 [**Nekoplay**](https://nekoplay.niupeis.com/wiki/) 创建的游戏而开发的。

## 待办事项：

- [x] Webview
- [x] 从 JS 关闭
- [x] 返回按钮
- [x] 吐司提示
- [x] 全面屏 "edge to edge"
- [x] 通知 (Beta)
- [x] 存储
- [ ] 快捷方式
- [ ] 广告接口 (?)

## 教程：Android Studio 模板使用指南

### 1. 更改包名

默认包名是 `com.niupleis.webview`，您应将其更改为您的域名和应用程序名称。

1. 在 Android Studio 中，右键单击 `com.niupleis.webview`
2. 选择 **Refactor → Rename**
3. 更改为您想要的名称（例如：`com.你的公司.你的游戏`）
4. 更新 `app/build.gradle.kts` 中的 `applicationId`：

```gradle
android {
    namespace = "com.niupleis.webview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.niupleis.webview"
        ---
    }
    ---
}
```

### 2. 添加您的游戏

将游戏的所有文件放置在 `app/src/main/assets/` 中。

**重要：** 主文件必须命名为 `index.html` 并位于 `assets/` 的根目录下。

目录结构示例：
```
app/src/main/assets/
├── index.html
├── styles.css
└── game.js
```

### 3. 应用程序样式

#### 背景

应用程序的背景颜色将取决于用户的主题：浅色主题下为白色，深色主题下为黑色。使用 CSS 更改此设置更容易。

#### 屏幕方向

在 `app/src/main/AndroidManifest.xml` 文件中更改 `manifest > application > activity : android:screenOrientation` 中的屏幕方向。
```
android:screenOrientation="sensor"
tools:ignore="LockedOrientationActivity">
<!-- landscape|portrait|sensorLandscape|sensorPortrait|sensor|unspecified -->
```

#### 图标

您可以按照 [本指南](https://developer.android.com/studio/write/create-app-icons) 在 Android Studio 中创建应用程序图标。
或者，您可以使用 [appicon.co](https://www.appicon.co/) 等在线工具从图像生成 mipmap，并将其导入到 `app/src/main/res/?` 中。

### 4. JavaScript 与 Android 的接口

该模板提供了一个 JavaScript 接口，用于与 Android 原生功能进行交互。

#### 可用功能：

**显示吐司（弹出消息）：**
```javascript
Android.showToast("来自 JavaScript 的问候！");
```

**关闭应用程序：**
```javascript
Android.closeGame();
```

**通知**
```javascript
Android.requestNotifications() // 请求通知权限
Android.notification("标题", "内容") // 创建基本通知
```

**存储**
```javascript
Android.fileSet('filename', 'content') // 保存文件
Android.fileGet('filename') // 读取文件
Android.fileDel('filename') // 删除文件
Android.fileExist('filename') // 检查文件是否存在
Android.fileSize('filename') // 获取文件大小
Android.fileList() // 所有文件列表
```

#### 处理返回按钮

在您的 JavaScript 代码中定义 `onBackPressed` 函数，以控制用户按下返回按钮时发生的情况：

```javascript
function onBackPressed() {
    // 在此处添加您的逻辑
    if (confirm("确定要退出吗？")) {
        Android.closeGame();
    }
}
```

## 完成

现在您可以在 Android Studio 中运行应用程序，并在真实设备或模拟器上测试您的游戏。

---

**注意：** 请记住在真实的 Android 设备或模拟器上测试 JavaScript 接口功能，因为这些功能在普通的 Web 浏览器中无法工作。
</details>

![Mimi-chan](https://niupleis.com/gallery/thumbnails/250927_MimiChan_dress.png)