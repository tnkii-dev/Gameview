# Gameview
Android Studio template for games made with Javascript.

## Info:

Esta plantilla te permite convertir tu juego HTML5 en una aplicación Android de manera sencilla.
Originalmente creada para compilar juegos creados con el motor de juegos de navegador [**Nekoplay**](https://nekoplay.niupeis.com/wiki/)

## To Do:

- [x] Webview
- [x] Cerrar desde js
- [x] Boton de volver
- [x] Toasts
- [x] Pantalla completa "edge to edge"
- [ ] Notificaciónes
- [ ] Almacenamiento
- [ ] Accesos directos
- [ ] Interfáz de Anuncios

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
