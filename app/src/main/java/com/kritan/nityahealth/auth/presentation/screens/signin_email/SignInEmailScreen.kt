package com.kritan.nityahealth.auth.presentation.screens.signin_email

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.auth.presentation.utils.AuthTopBar
import com.kritan.nityahealth.base.utils.UiEvent
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInEmailScreen(
    viewModel: SignInEmailViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToDashboard: () -> Unit,

    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    var isPasswordMasked by remember {
        mutableStateOf(true)
    }

    fun togglePasswordMask() {
        isPasswordMasked = !isPasswordMasked
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar ->
                    snackbarHostState.showSnackbar(it.message)

                is UiEvent.NavigateToDashboard -> navigateToDashboard()
                else -> Unit
            }
        }
    }

    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        Scaffold(
            topBar = { AuthTopBar(onNavigateUp = navigateUp) },
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
                        keyboardType = KeyboardType.Email,
                        onValueChange = viewModel::onEmailUpdate,
                        supportingText = viewModel.uiState.currentEmailErrors.firstOrNull(),
                        isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
                    )
                }

                item {
                    MyTextField(
                        label = "Password",
                        value = viewModel.uiState.currentPassword,
                        keyboardType = KeyboardType.Password,
                        onValueChange = viewModel::onPasswordUpdate,
                        isPassword = true,
                        isPasswordMasked = isPasswordMasked,
                        isLastField = true,
                        trailingIconAction = ::togglePasswordMask,
                    )
                }

                item {
                    MyButton(label = "Sign in", onClick = { viewModel.loginUser() })
                }

                item {
                    AuthFooter(
                        text = "Don’t have account yet?",
                        buttonText = "Sign Up",
                        onClick = navigateToSignUp
                    )
                }
            }
        }
    }
}
