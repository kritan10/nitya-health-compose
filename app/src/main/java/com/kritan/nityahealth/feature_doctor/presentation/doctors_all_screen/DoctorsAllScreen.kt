package com.kritan.nityahealth.feature_doctor.presentation.doctors_all_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MySearchBar
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun DoctorsAllScreen(navigateUp: () -> Unit) {
    MyScaffoldLayout(title = "Doctors", navigateUp = navigateUp) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    MySearchBar(placeholder = "Search for doctors")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            items(5) {
                DoctorCard()
            }
        }
    }
}

@Composable
private fun DoctorCard() {
    Row(
        Modifier
            .border(1.dp, Color.LightGray)
            .padding(24.dp)
    ) {
        Column {
            Image(painterResource(R.drawable.img_doctors_doctor), "", Modifier.size(100.dp))
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text("Next Available")
            Text("Tomorrow")
        }

        Column {
            Text("Doctor Name")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(Icons.Default.Favorite, "", tint = Color(0xFFFF0000))
        }
    }

}