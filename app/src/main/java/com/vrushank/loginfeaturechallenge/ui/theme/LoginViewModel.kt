package com.vrushank.loginfeaturechallenge.ui.theme

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vrushank.loginfeaturechallenge.LoginScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(): ViewModel() {

    val _uiState = MutableStateFlow(LoginDetail())
    val uiState: StateFlow<LoginDetail> = _uiState

    fun updateEmail(email: String){
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun updatePassword(password:  String){
        _uiState.update {
            it.copy(password = password)
        }
    }
    companion object{
        fun factory() = viewModelFactory {
            addInitializer(LoginViewModel::class){
                LoginViewModel()
            }
        }
    }

}

data class LoginDetail(val email:String ="", val password: String ="",val isEnabled: Boolean =false)