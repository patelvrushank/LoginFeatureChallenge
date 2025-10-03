package com.vrushank.loginfeaturechallenge

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vrushank.loginfeaturechallenge.domain.useCases.ForgotPassword
import com.vrushank.loginfeaturechallenge.domain.useCases.LoginUseCase
import com.vrushank.loginfeaturechallenge.domain.useCases.Logout
import com.vrushank.loginfeaturechallenge.presentation.ForgotPasswordScreen
import com.vrushank.loginfeaturechallenge.presentation.LandingScreen
import com.vrushank.loginfeaturechallenge.presentation.LoginScreen
import com.vrushank.loginfeaturechallenge.presentation.LoginViewModel
import com.vrushank.loginfeaturechallenge.presentation.UiEvent
import com.vrushank.loginfeaturechallenge.ui.theme.LoginFeatureChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginFeatureChallengeTheme {
                val vm: LoginViewModel = viewModel(
                    factory = LoginViewModel.factory(
                        loginUseCase = LoginUseCase(),
                        logout = Logout(),
                        forgotPassword = ForgotPassword(),
                    )
                )
                val uiData = vm.uiState.collectAsState()
                val navController = rememberNavController()
                NavHost(navController, "login_screen") {

                    composable("login_screen") {
                        val context = LocalContext.current
                        LaunchedEffect(Unit) {
                            vm.uiEvent.collect {
                                when (it) {
                                    is UiEvent.Success -> navController.navigate("landing_screen")
                                    is UiEvent.ForrgotPassword -> navController.navigate("forgot_password_screen")
                                }

                            }
                        }


                        LoginScreen(
                            emailValue = uiData.value.email,
                            onChangeEmail = { vm.updateEmail(it) },
                            passValue = uiData.value.password,
                            onChangePass = { vm.updatePassword(it) },
                            isEnable = uiData.value.email.isNotEmpty() && uiData.value.password.length >= 6,
                            isLoading = uiData.value.isLoading,
                            loginOnClick = {
                                vm.onLogin(
                                    uiData.value.email, uiData.value.password
                                )
                            },
                            uiData.value.errorMsg,
                            onClickForgotPassword = {
                                vm.forgotPasswordClick()
                            })

                    }
                    composable("landing_screen") {

                        LandingScreen()
                    }
                    composable("forgot_password_screen") {
                        ForgotPasswordScreen(
                            email = "",
                            onValuesChange = {}
                        )
                    }

                }


            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val vm: LoginViewModel = viewModel(factory = LoginViewModel.factory())
    val uiData = vm.uiState.collectAsState()
    LoginScreen(
        emailValue = uiData.value.email,
        onChangeEmail = { vm.updateEmail(it) },
        passValue = uiData.value.password,
        onChangePass = { vm.updatePassword(it) },
        isEnable = uiData.value.email.isNotEmpty() && uiData.value.password.length >= 6,
        isLoading = uiData.value.isLoading
    )
}*/
