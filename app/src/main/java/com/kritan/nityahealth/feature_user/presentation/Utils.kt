package com.kritan.nityahealth.feature_user.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.FileProvider
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): Uri {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    val file = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return FileProvider.getUriForFile(
        Objects.requireNonNull(this),
        "com.kritan.nityahealth.provider",
        file
    )
}


sealed class Launcher() {
    object GalleryLauncher : Launcher()
    object CameraLauncher : Launcher()
    data class FileLauncher(val launcher: ManagedActivityResultLauncher<String, Uri>) : Launcher()
}

@Composable
fun getCustomLauncherForActivityResult(
    launcher: Launcher,
    imageCropperLauncher: ManagedActivityResultLauncher<CropImageContractOptions, CropImageView.CropResult>
): ManagedActivityResultLauncher<String, Boolean> {
    return rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            when (launcher) {
                is Launcher.CameraLauncher -> {
                    imageCropperLauncher.launch(
                        CropImageContractOptions(
                            uri = null,
                            cropImageOptions = CropImageOptions(
                                imageSourceIncludeGallery = false,
                                imageSourceIncludeCamera = true
                            )
                        )
                    )
                }

                is Launcher.GalleryLauncher -> {
                    imageCropperLauncher.launch(
                        CropImageContractOptions(
                            uri = null,
                            cropImageOptions = CropImageOptions(
                                imageSourceIncludeGallery = true,
                                imageSourceIncludeCamera = false
                            )
                        )
                    )
                }

                is Launcher.FileLauncher -> {
                    launcher.launcher.launch("image/")
                }
            }
        }
    }
}