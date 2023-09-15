package com.kritan.nityahealth.feature_user.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.kritan.nityahealth.auth.AppAuth
import com.kritan.nityahealth.auth.data.repository.FacebookRepository
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.UiEvent
import com.kritan.nityahealth.feature_user.data.models.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appAuth: AppAuth,
    private val facebookRepository: FacebookRepository
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    var uiState by mutableStateOf(ProfileScreenState())
        private set

    fun logOutUser() {
        viewModelScope.launch {
            appAuth.logOutUser()
            _uiEvent.send(UiEvent.NavigateToSignIn)
        }
    }

    fun getUserDataFromFacebook() {
        viewModelScope.launch {
            val accessToken = AccessToken.getCurrentAccessToken()?.token ?: ""
            val userId = AccessToken.getCurrentAccessToken()?.userId ?: ""
            facebookRepository.getUserData(accessToken, userId).collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> uiState = uiState.copy(isLoading = response.isLoading)
                    is Resource.Success -> {
                        val fbUser = response.data!!
                        uiState = uiState.copy(
                            userData = UserData(
                                name = fbUser.name ?: "",
                                gender = fbUser.gender ?: "",
                                address = fbUser.location ?: "",
                                contact = "984123456",
                                email = fbUser.email ?: "",
                                image = fbUser.picture?.data?.url ?: "",
                            )
                        )
                    }
                }

            }
        }
    }
}