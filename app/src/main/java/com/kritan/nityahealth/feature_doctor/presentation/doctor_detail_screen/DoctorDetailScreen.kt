package com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailScreen(
    navigateUp: () -> Unit,
    viewModel: DoctorDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Doctor", navigateUp = navigateUp)
    }) { pv ->
        Column(Modifier.padding(top = pv.calculateTopPadding() - 20.dp)) {
            if (viewModel.state.isLoading) {
                CircularProgressIndicator(
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            } else {
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
                Column(Modifier.padding(20.dp)) {
                    Text(doctor.name)
                    Text(doctor.position)
                    if (doctor.location.isNotEmpty()) {
                        Text(doctor.location[0].name!!)
                    }
                }
            }
        }
    }
}