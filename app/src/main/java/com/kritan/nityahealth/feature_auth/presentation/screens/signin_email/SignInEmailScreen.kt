package com.kritan.nityahealth.feature_auth.presentation.screens.signin_email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.base.utils.UiEvent
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthTopBar
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInEmailScreen(
    viewModel: SignInEmailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    authenticateUser: (AuthState) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar ->
                    snackbarHostState.showSnackbar(it.message)
            }
        }
    }

    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        Scaffold(
            topBar = { AuthTopBar(onNavigateUp = onNavigateUp) },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { pv ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(pv.calculateTopPadding()))
                    Text("Sign In")
                }

                item {
                    MyTextField(
                        label = "Email address",
                        value = viewModel.uiState.currentEmail,
                        isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
                        onValueChange = viewModel::onEmailUpdate
                    )
                }

                item {
                    MyTextField(
                        label = "Password",
                        value = viewModel.uiState.currentPassword,
                        isLast = true,
                        onValueChange = viewModel::onPasswordUpdate
                    )
                }

                item {
                    MyButton(label = "Sign in", onClick = { viewModel.loginUser(authenticateUser) })
                }

                item {
                    AuthFooter(
                        text = "Don’t have account yet?",
                        buttonText = "Sign Up",
                        onClick = onNavigateToSignUp
                    )
                }
            }
        }
    }
}
