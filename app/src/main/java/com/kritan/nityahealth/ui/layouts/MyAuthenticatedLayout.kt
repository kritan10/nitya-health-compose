package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.components.MyButton

@Composable
fun MyAuthenticatedLayout(
    isAuth: Boolean,
    navigateToSignIn: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (isAuth) {
        content()
    } else {
        UnauthenticatedScreen(navigateToSignIn)
    }
}


@Composable
private fun UnauthenticatedScreen(navigateToSignIn: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("You need to login in order to access this feature.")
        Spacer(modifier = Modifier.height(16.dp))
        MyButton(label = "Sign In", onClick = navigateToSignIn)
    }
}