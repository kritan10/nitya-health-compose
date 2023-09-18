package com.kritan.nityahealth.auth.presentation.screens.signin

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
    onSignInEmail: () -> Unit,
    onSkipSignIn: () -> Unit,
) {

    // --- Facebook Sign In : Start --- //
    val fbLoginLauncher = rememberLauncherForActivityResult(
        contract = viewModel.getFacebookLoginContract(),
        onResult = {
            Log.d("Launcher", "Launcher")
        }
    )

    fun signInWithFacebook() {
        val permissions = listOf("email", "public_profile")
        fbLoginLauncher.launch(permissions)
    }
    // --- Facebook Sign In : End --- //


    // --- Google Sign In : Start --- //
    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = viewModel::onGoogleSignInActivityLauncherResult
    )

    fun signInWithGoogle() {
        viewModel.signInWithGoogle(activityLauncher)
    }
    // --- Google Sign In : End --- //


    LaunchedEffect(Unit) {
        if (!viewModel.isUserBoarded) viewModel.boardInUser()
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

//          Email Sign in Button
            MyButton(label = "Sign in with Email", onClick = onSignInEmail)

            Text("or", fontSize = 14.sp, lineHeight = 15.61.sp)

//          Facebook Sign in Button
            MyButton(label = "Sign in with Facebook", onClick = ::signInWithFacebook)

//          Google Sign in Button
            MyButton(label = "Sign in with Google", onClick = ::signInWithGoogle)

            MyTextButton(
                label = "Skip",
                textColor = MaterialTheme.colorScheme.primary,
                onClick = onSkipSignIn
            )

            AuthFooter(
                text = "By signing in, you accept our ",
                buttonText = "Terms and Conditions",
                onClick = {}
            )
        }
    }
}
