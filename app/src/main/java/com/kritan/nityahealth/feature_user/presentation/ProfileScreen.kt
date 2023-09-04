package com.kritan.nityahealth.feature_user.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MyListItem
import com.kritan.nityahealth.ui.components.MyTopAppBar
import com.kritan.nityahealth.ui.layouts.MyTitleBodyLayout
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navigateUp: () -> Unit) {
    Scaffold(topBar = {
        MyTopAppBar(
            title = "My Profile",
            navigateUp = navigateUp
        )
    }) { pv ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier
                .padding(pv)
        ) {
            sectionUserImage()
            sectionPersonalDetails()
            sectionHealthDetails()
            sectionMedicalCondition()
            item { Spacer(Modifier.height(12.dp)) }
        }
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

private fun LazyListScope.sectionPersonalDetails() {
    val personalDetails = listOf(
        IconFieldValue(R.drawable.ic_dashboard_profile, "Gender", "Female"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Address", "Bhaktapur"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Contact", "9845123457"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Email", "user@email.com"),
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

private fun LazyListScope.sectionUserImage() {
    item {
        Image(
            painter = painterResource(R.drawable.img_userimage),
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}







