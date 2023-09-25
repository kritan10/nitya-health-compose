package com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.constants.MyPadding
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun DoctorDetailScreen(
    navigateUp: () -> Unit,
    viewModel: DoctorDetailViewModel = hiltViewModel()
) {
    MyLoadingLayout(loading = viewModel.state.isLoading) {
        MyScaffoldLayout(title = "Doctors", navigateUp = navigateUp, padding = MyPadding.None, clipTopWithContent = true) {
            val doctor = viewModel.state.doctor!!
            Box {
                AsyncImage(
                    doctor.image,
                    "",
                    Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                ) {
                    MyButton(
                        label = "Voice Call",
                        isFullLength = false,
                        height = 40,
                    ) {}
                    Spacer(Modifier.width(28.dp))
                    MyButton(
                        label = "Message",
                        isFullLength = false,
                        height = 40,
                    ) {}
                }
            }

            Column(Modifier.padding(MyPadding.Default)) {
                Text(doctor.name)
                Text(doctor.position)
                if (doctor.location.isNotEmpty()) {
                    Text(doctor.location[0].name!!)
                }
            }

        }
    }
}
