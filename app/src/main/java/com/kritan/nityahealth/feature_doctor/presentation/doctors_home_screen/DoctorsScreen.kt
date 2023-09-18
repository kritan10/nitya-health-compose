package com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
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
import com.kritan.nityahealth.R
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.ui.components.MyClickableText
import com.kritan.nityahealth.ui.components.MySearchBar
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@Composable
fun DoctorsScreen(
    viewModel: DoctorsScreenViewModel,
    navigateToAllDoctors: () -> Unit,
    navigateToDoctorDetails: (Int) -> Unit
) {
    val state = viewModel.state

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {

        //Empty space from Arrangement
        item {}

        // Search bar
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Find your desired doctor", style = MaterialTheme.typography.titleLarge)
                MySearchBar(placeholder = "Search for doctors")
            }
        }

        // Categories
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Categories", style = MaterialTheme.typography.titleLarge)
                LazyRow {
                }
            }
        }

        // Top doctors
        item {
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
                MyLoadingLayout(loading = viewModel.state.isLoading) {
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
            Text(doctor.name ?: "", style = MaterialTheme.typography.titleMedium)
            Text(
                "${doctor.position} - ${doctor.qualification}",
                color = Color(0xA6000000),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
            )
        }

    }
}