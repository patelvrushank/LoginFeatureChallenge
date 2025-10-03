package com.vrushank.loginfeaturechallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vrushank.loginfeaturechallenge.domain.model.User
import com.vrushank.loginfeaturechallenge.domain.useCases.ForgotPassword
import com.vrushank.loginfeaturechallenge.domain.useCases.LoginUseCase
import com.vrushank.loginfeaturechallenge.domain.useCases.Logout
import com.vrushank.loginfeaturechallenge.util.Resources
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val logout: Logout,
    private val forgotPassword: ForgotPassword
) : ViewModel() {


    val _uiState = MutableStateFlow(LoginDetail())
    val uiState: StateFlow<LoginDetail> = _uiState

    val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun onLogin(email:String, password: String){
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = loginUseCase.invoke(email,password)
            _uiState.update {
                delay(5000L)
                it.copy(isLoading = false)
            }
            when(result){
                is Resources.Error<*> -> {_uiState.update { it.copy(errorMsg = result.error) }}
                is Resources.Loading<*> -> {}
                is Resources.Success<User> ->  {_uiEvent.send(UiEvent.Success)
                result.data?.copy()}

            }

        }
    }
    fun forgotPasswordClick(){
        viewModelScope.launch {  _uiEvent.send(UiEvent.ForrgotPassword) }

    }


    companion object {
        fun factory(loginUseCase: LoginUseCase,logout: Logout,forgotPassword: ForgotPassword) = viewModelFactory {
            addInitializer(LoginViewModel::class) {
                LoginViewModel(
                    loginUseCase = loginUseCase,
                    logout = logout,
                    forgotPassword = forgotPassword
                )
            }
        }
    }

}

data class LoginDetail(
    val email: String = "",
    val password: String = "",
    val isEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMsg: String? = ""
)

sealed class UiEvent(){
    object Success: UiEvent()
    object ForrgotPassword: UiEvent()

}