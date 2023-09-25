package com.kritan.nityahealth.base.camera

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.Camera
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraState
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import com.kritan.nityahealth.R
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MyCamera(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val surfaceProvider: Preview.SurfaceProvider?
) {
    companion object{
        const val TAG = "CAMERA"
        const val DATE_FORMAT = "yyyy_MM_dd_HH_mm_ss"
        const val PHOTO_FORMAT = "image/jpeg"
    }

    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var camera: Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null

    /** Blocking camera operations are performed using this executor */
    private var cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        setUpCamera()
    }

    private fun setUpCamera() {
        cameraProvider = ProcessCameraProvider.getInstance(context).get()

        // Build and bind the camera use cases
        bindCameraUseCases()
    }

    /** Declare and bind preview and capture use cases */
    private fun bindCameraUseCases() {
        // CameraProvider
        val cameraProvider = this.cameraProvider
            ?: throw IllegalStateException("Camera initialization failed.")

        // CameraSelector
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

        // Preview
        preview = Preview.Builder().build()

        // ImageCapture
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        // Must unbind the use-cases before rebinding them
        cameraProvider.unbindAll()

        if (camera != null) {
            // Must remove observers from the previous camera instance
            removeCameraStateObservers(camera!!.cameraInfo)
        }

        try {
            // A variable number of use-cases can be passed here -
            // camera provides access to CameraControl & CameraInfo
            camera = cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, preview, imageCapture,
            )

            // Attach the viewfinder's surface provider to preview use case
            preview?.setSurfaceProvider(surfaceProvider)

//            Use only for debug
//            observeCameraState(camera?.cameraInfo!!)
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }


    private fun removeCameraStateObservers(cameraInfo: CameraInfo) {
        cameraInfo.cameraState.removeObservers(lifecycleOwner)
    }

    @SuppressLint
    private fun observeCameraState(cameraInfo: CameraInfo) {
        cameraInfo.cameraState.observe(lifecycleOwner) { cameraState ->
            run {
                when (cameraState.type) {
                    CameraState.Type.PENDING_OPEN -> {
                        // Ask the user to close other camera apps
                        Toast.makeText(
                            context,
                            "CameraState: Pending Open",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.Type.OPENING -> {
                        // Show the Camera UI
                        Toast.makeText(
                            context,
                            "CameraState: Opening",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.Type.OPEN -> {
                        // Setup Camera resources and begin processing
                        Toast.makeText(
                            context,
                            "CameraState: Open",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.Type.CLOSING -> {
                        // Close camera UI
                        Toast.makeText(
                            context,
                            "CameraState: Closing",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.Type.CLOSED -> {
                        // Free camera resources
                        Toast.makeText(
                            context,
                            "CameraState: Closed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            cameraState.error?.let { error ->
                when (error.code) {
                    // Open errors
                    CameraState.ERROR_STREAM_CONFIG -> {
                        // Make sure to setup the use cases properly
                        Toast.makeText(
                            context,
                            "Stream config error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Opening errors
                    CameraState.ERROR_CAMERA_IN_USE -> {
                        // Close the camera or ask user to close another camera app that's using the
                        // camera
                        Toast.makeText(
                            context,
                            "Camera in use",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.ERROR_MAX_CAMERAS_IN_USE -> {
                        // Close another open camera in the app, or ask the user to close another
                        // camera app that's using the camera
                        Toast.makeText(
                            context,
                            "Max cameras in use",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.ERROR_OTHER_RECOVERABLE_ERROR -> {
                        Toast.makeText(
                            context,
                            "Other recoverable error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Closing errors
                    CameraState.ERROR_CAMERA_DISABLED -> {
                        // Ask the user to enable the device's cameras
                        Toast.makeText(
                            context,
                            "Camera disabled",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    CameraState.ERROR_CAMERA_FATAL_ERROR -> {
                        // Ask the user to reboot the device to restore camera function
                        Toast.makeText(
                            context,
                            "Fatal error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Closed errors
                    CameraState.ERROR_DO_NOT_DISTURB_MODE_ENABLED -> {
                        // Ask the user to disable the "Do Not Disturb" mode, then reopen the camera
                        Toast.makeText(
                            context,
                            "Do not disturb mode enabled",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun takePicture() {
        imageCapture?.let { imageCapture ->
            // Create time stamped name and MediaStore entry.
            val name = SimpleDateFormat(DATE_FORMAT, Locale.US)
                .format(System.currentTimeMillis())
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, PHOTO_FORMAT)
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    val appName = context.resources.getString(R.string.app_name)
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/${appName}")
                }
            }

            // Create output options object which contains file + metadata
            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(
                    context.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )
                .build()

            // Setup image capture listener which is triggered after photo has been taken
            imageCapture.takePicture(
                outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = output.savedUri
                        Log.d(TAG, "Photo capture succeeded: $savedUri")
                    }
                }
            )
        }
    }
}