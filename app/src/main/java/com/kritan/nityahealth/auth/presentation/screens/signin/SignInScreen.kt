package com.kritan.nityahealth.auth.presentation.screens.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.R
import com.kritan.nityahealth.auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextButton

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignInEmail: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.appAuth.completeOnboarding()
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(20.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.main_icon),
                null,
                modifier = Modifier.size(150.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                "Welcome",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Text("Sign in to continue", style = MaterialTheme.typography.labelMedium)
            MyButton(label = "Sign in with Email") {
                onSignInEmail()
            }
            Text("or", fontSize = 14.sp, lineHeight = 15.61.sp)
            MyButton(label = "Sign in with Facebook", leading = Icons.Default.Call) {
            }
            MyTextButton(label = "Skip", textColor = MaterialTheme.colorScheme.primary) {
            }
            AuthFooter(
                text = "By signing in, you accept our ",
                buttonText = "Terms and Conditions",
                onClick = {})
        }
    }

}