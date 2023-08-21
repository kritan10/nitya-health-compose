package com.kritan.nityahealth.feature_user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.commons.components.MyListItem
import com.kritan.nityahealth.commons.components.MyTitleBodyLayout
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(toggleDrawer: () -> Unit) {
    Scaffold(topBar = {
        MyTopAppBar(
            title = "My Profile",
            toggleDrawer = toggleDrawer
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
        }
    }
}


data class IconFieldValue(val icon: Int, val field: String, val value: String)

private fun LazyListScope.sectionMedicalCondition() {
    val diseaseDetails = listOf(
        IconFieldValue(R.drawable.ic_dashboard_profile, "Gender", "Female"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Address", "Bhaktapur"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Contact", "9845123457"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Email", "user@email.com"),
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
        IconFieldValue(R.drawable.ic_dashboard_profile, "Height", "Female"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Weight", "Bhaktapur"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Blood Group", "9845123457"),
        IconFieldValue(R.drawable.ic_dashboard_profile, "Food Type", "user@email.com"),
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
        Box(
            Modifier
                .width(150.dp)
                .height(150.dp)
        ) { }
    }
}







