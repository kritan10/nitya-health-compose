# Nitya Health - Jetpack Compose Application

Nitya Health is a clean-architecture health & lifestyle application built on Compose, the modern UI library for Android. It follows a custom design style and self-crafted UI components.

### The following are the libraries used in this application:
- Dependency injection using [**Dagger Hilt**](https://dagger.dev/hilt/).
- Navigation using [**Compose Navigation**](https://developer.android.com/jetpack/compose/navigation).
- Network calls using the [**Retrofit**](https://square.github.io/retrofit/) library and [**ktor**](https://ktor.io/docs/getting-started-ktor-client.html) client.
- Authentication using [**Google One-Tap**](https://developers.google.com/identity/gsi/web/guides/features) and [**Facebook Login**](https://developers.facebook.com/docs/facebook-login/android).
- Asynchronous image loading over network using [**Coil**](https://github.com/coil-kt/coil).
- Local preferences management using [**DataStore**](https://developer.android.com/jetpack/androidx/releases/datastore).
- Local data read and write using [**RoomDB**](https://developer.android.com/jetpack/androidx/releases/datastore).
- Image cropping using [**CanHub Image Cropper**](https://github.com/CanHub/Android-Image-Cropper).

### This following are the features of this application:.
- Built following clean architecture principles and best practices.
- Client state auth management, custom login feature as well as third-party login features.
- Various commonly used form elements including a multistep form.
- Interaction with different Android components such as Camera, Gallery and File Picker.
- Custom splash screen integrating application initialization.
- Features a minified build of the application optimized using R8 and custom Proguard rules.
- Custom camera implementation with CameraX Use Cases.
- Facebook login including profile update based on user Facebook account.
