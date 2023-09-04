package com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MyClickableText
import com.kritan.nityahealth.ui.components.MyTopAppBar
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.ui.components.MySearchBar
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorsScreen(
    navigateUp: () -> Unit,
    navigateToAllDoctors: () -> Unit,
    navigateToDoctorDetails: (Int) -> Unit
) {
    val viewModel = hiltViewModel<DoctorsScreenViewModel>()
    val state = viewModel.state
    Scaffold(topBar = {
        MyTopAppBar(title = "Doctors", navigateUp)
    }) { pv ->

        Column(
            Modifier.padding(top = pv.calculateTopPadding(), start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {}
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Find your desired doctor", style = MaterialTheme.typography.titleLarge)
                MySearchBar(placeholder = "Search for doctors")
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Categories", style = MaterialTheme.typography.titleLarge)
                LazyRow {
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Top Doctors", style = MaterialTheme.typography.titleLarge)
                    MyClickableText(
                        label = "View all",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                        onClick = navigateToAllDoctors
                    )
                }
                if (state.isLoading) {
                    Spacer(Modifier.height(40.dp))
                    CircularProgressIndicator(
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.TopCenter)
                    )
                } else {
                    state.doctors.forEach { doctor ->
                        TopDoctorsCard(doctor, navigateToDoctorDetails)
                    }
                }

            }
        }
    }
}

@Composable
private fun TopDoctorsCard(doctor: Doctor, navigateToDoctorDetails: (Int) -> Unit) {
    Row(
        Modifier
            .clickable {
                navigateToDoctorDetails(doctor.id!!)
            }
            .fillMaxWidth()
            .border(1.dp, Color.Black, mRoundedCorner)
            .padding(horizontal = 24.dp, vertical = 10.dp),
        Arrangement.Start,
        Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_doctors_doctor),
            contentDescription = "Doctor",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(doctor.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "${doctor.position} - ${doctor.qualification}",
                color = Color(0xA6000000),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
            )
        }

    }
}