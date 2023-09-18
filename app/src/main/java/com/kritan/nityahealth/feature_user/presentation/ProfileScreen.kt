package com.kritan.nityahealth.feature_user.presentation

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContract
import com.kritan.nityahealth.R
import com.kritan.nityahealth.base.utils.UiEvent
import com.kritan.nityahealth.feature_user.data.models.UserData
import com.kritan.nityahealth.ui.components.MyBottomSheet
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyDialog
import com.kritan.nityahealth.ui.components.MyIconTextButton
import com.kritan.nityahealth.ui.components.MyListItem
import com.kritan.nityahealth.ui.layouts.MyAuthenticatedLayout
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout
import com.kritan.nityahealth.ui.layouts.MyTitleBodyLayout
import com.kritan.nityahealth.ui.theme.mRoundedCorner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToSignIn: () -> Unit,
    isAuth: Boolean,
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    fun openDialog() = run { isDialogOpen = true }
    fun closeDialog() = run { isDialogOpen = false }

    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    fun openSheet() = run { isSheetOpen = true }
    fun closeSheet() = run {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                isSheetOpen = false
            }
        }
    }

    var capturedImageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val imageCropperLauncher =
        rememberLauncherForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                capturedImageUri = result.uriContent
            }
        }

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { capturedImageUri = uri }
        }
    )

    val cameraLauncher = getCustomLauncherForActivityResult(
        launcher = Launcher.CameraLauncher,
        imageCropperLauncher = imageCropperLauncher
    )

    val galleryLauncher = getCustomLauncherForActivityResult(
        launcher = Launcher.GalleryLauncher,
        imageCropperLauncher = imageCropperLauncher
    )

    fun launchActivityAndCloseSheet(action: () -> Unit) {
        scope.launch {
            action()
            // to launch a activity before the sheet closes
            delay(2000)
            closeSheet()
        }
    }

    fun launchCamera() {
        launchActivityAndCloseSheet {
            cameraLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun launchGallery() {
        launchActivityAndCloseSheet {
            val permission =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }

            galleryLauncher.launch(permission)
        }
    }

    fun launchFilePicker() {
        launchActivityAndCloseSheet {
            fileLauncher
        }
    }


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                UiEvent.NavigateToSignIn -> navigateToSignIn()
                else -> Unit
            }
        }
    }

    LaunchedEffect(Unit){
        viewModel.getUserDataFromFacebook()
    }

    //Log-out dialog
    MyDialog(
        isDialogOpen = isDialogOpen,
        title = "Log Out",
        text = "Are you sure you want to log out?",
        onDismissRequest = ::closeDialog,
        onConfirmButton = viewModel::logOutUser,
        onDismissButton = ::closeDialog
    )

    //Bottom sheet
    MyBottomSheet(
        isSheetOpen = isSheetOpen,
        sheetState = sheetState,
        title = "Profile photo",
        closeSheet = ::closeSheet,
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            item {
                MyIconTextButton(
                    icon = Icons.Filled.CameraAlt, text = "Camera",
                    onClick = ::launchCamera
                )
            }

            item {
                MyIconTextButton(
                    icon = Icons.Filled.Photo, text = "Gallery",
                    onClick = ::launchGallery
                )
            }

            item {
                MyIconTextButton(
                    icon = Icons.Filled.Folder, text = "Files",
                    onClick = {}
                )
            }
        }
    }

    //UI
    MyScaffoldLayout(title = "My Profile", navigateUp = navigateUp) {
        MyLoadingLayout(loading = viewModel.uiState.isLoading) {
            MyAuthenticatedLayout(isAuth = isAuth, navigateToSignIn = navigateToSignIn) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    contentPadding = PaddingValues(20.dp),
                ) {
                    sectionUserImage(::openSheet, capturedImageUri, viewModel.uiState.userData.image)
                    sectionPersonalDetails(viewModel.uiState.userData)
                    sectionHealthDetails()
                    sectionMedicalCondition()
                    item { Spacer(Modifier.height(12.dp)) }
                    item {
                        MyButton(label = "Log Out", onClick = ::openDialog)
                    }
                }
            }
        }
    }
}

private fun LazyListScope.sectionUserImage(
    openBottomSheet: () -> Unit,
    imageUri: Uri,
    image: String
) {
    val isUriNull = imageUri.path?.isEmpty() == true
    item {
        AsyncImage(
            model = if (isUriNull) image else imageUri,
            contentDescription = "",
            modifier = Modifier
                .clickable(onClick = openBottomSheet)
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}


data class IconFieldValue(val icon: Int, val field: String, val value: String)

private fun LazyListScope.sectionMedicalCondition() {
    val diseaseDetails = listOf(
        IconFieldValue(R.drawable.ic_dashboard_profile, "Disease Name", "Migraine"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Type", "Headache"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Cured?", "No"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Medicine Name", "Bruffin"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Medicine Duration", "2 Weeks"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Other Medicines?", "Yes"),
    )
    item {
        MyTitleBodyLayout("Medical Condition") {
            diseaseDetails.forEachIndexed { index, item ->
                if (index != 0) Spacer(modifier = Modifier.height(8.dp))
                MyListItem(
                    leading = item.icon,
                    title = item.field,
                    trailing = item.value,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary.copy(
                                0.1F
                            )
                        )
                        .padding(vertical = 8.dp, horizontal = 20.dp)
                )
            }
        }
    }
}

private fun LazyListScope.sectionPersonalDetails(userData: UserData) {
    val personalDetails = listOf(
        IconFieldValue(R.drawable.ic_dashboard_profile, "Gender", userData.gender),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Address", userData.address),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Contact", userData.contact),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Email", userData.email),
    )
    item {
        MyTitleBodyLayout(title = "Personal Details") {
            Column(Modifier.clip(mRoundedCorner)) {
                personalDetails.forEachIndexed { index, item ->
                    if (index != 0) {
                        Divider(Modifier, 2.dp, Color.Gray.copy(0.3F))
                    }

                    MyListItem(
                        leading = item.icon,
                        title = item.field,
                        trailing = item.value,
                    )
                }
            }

        }
    }
}

private fun LazyListScope.sectionHealthDetails() {
    val healthDetails = listOf(
        IconFieldValue(R.drawable.ic_dashboard_profile, "Height", "5.9 ft"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Weight", "72 kg"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Blood Group", "AB +ve"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Food Type", "Vegan"),
    )
    item {
        MyTitleBodyLayout(title = "Health Details") {
            Column(Modifier.clip(mRoundedCorner)) {
                healthDetails.forEach { item ->
                    MyListItem(
                        leading = item.icon,
                        title = item.field,
                        trailing = item.value,
                    )
                }
            }
        }
    }
}









