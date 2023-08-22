package com.kritan.nityahealth.ui.layouts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MyGridItemCard

data class MyGridItem(val title: String, @DrawableRes val image: Int, val onClick: () -> Unit)

@Composable
fun MyGridLayout(gridItems: List<MyGridItem> = sampleItems, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(gridItems) { item ->
                MyGridItemCard(item.title, item.image, item.onClick)
            }
        }
    }
}

private val sampleItems = listOf(
    MyGridItem(
        "Sample 1",
        R.drawable.ic_dashboard_wellness,
        {}
    ),
    MyGridItem(
        "Sample 2",
        R.drawable.ic_dashboard_consultants,
        {}
    ),
    MyGridItem(
        "Sample 3",
        R.drawable.ic_dashboard_topics,
        {}
    ),
    MyGridItem(
        "Sample 4",
        R.drawable.ic_dashboard_news,
        {}
    ),
    MyGridItem(
        "Sample 5",
        R.drawable.ic_dashboard_activities,
        {}
    ),
    MyGridItem(
        "Sample 6",
        R.drawable.ic_dashboard_profile,
        {}
    ),
)
