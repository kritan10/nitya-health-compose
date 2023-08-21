package com.kritan.nityahealth.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.commons.utils.routes.Routes
import com.kritan.nityahealth.ui.theme.mRoundedCornerDrawerHeader

data class DrawerItem(val label: String, val route: Routes)

private val drawerItems = listOf(
    DrawerItem("Dashboard", Routes.Dashboard),
    DrawerItem("Profile", Routes.Profile),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    closeDrawer: () -> Unit,
    navigateTo: (Routes) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = Color.Black.copy(0.1F),
        drawerContent = {
            Surface(color = MaterialTheme.colorScheme.background) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                ) {
                    item {
                        MyDrawerHeader()
                    }
                    val a = 1

                    items(drawerItems) {
                        MyDrawerItem(
                            it.label,
                            currentRoute == it.route.route
                        ) { navigateTo(it.route);closeDrawer() }
                    }
                }
            }
        }
    )

    {
        content()
    }
}


@Composable
fun MyDrawerHeader() {
    Row(
        Modifier
            .clip(mRoundedCornerDrawerHeader)
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Icon(Icons.Default.AccountCircle, "")
        Column {
            Text("John Doe")
            Text("Kathmandu, Nepal")
        }
    }
}


@Composable
fun MyDrawerItem(label: String, isSelected: Boolean, navigateTo: () -> Unit) {
    var modifier = Modifier
        .clickable { navigateTo() }
        .fillMaxWidth()
    if (isSelected) modifier = modifier.background(MaterialTheme.colorScheme.primary)
    modifier = modifier.padding(horizontal = 20.dp, vertical = 16.dp)

    Text(
        label,
        modifier
    )
}

