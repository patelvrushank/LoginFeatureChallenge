package com.vrushank.loginfeaturechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vrushank.loginfeaturechallenge.ui.theme.LoginFeatureChallengeTheme
import com.vrushank.loginfeaturechallenge.ui.theme.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginFeatureChallengeTheme {
                val vm: LoginViewModel = viewModel(factory = LoginViewModel.factory())
                val uiData = vm.uiState.collectAsState()
                LoginScreen(
                    emailValue = uiData.value.email,
                    onChangeEmail = {vm.updateEmail(it)},
                    passValue = uiData.value.password,
                    onChangePass = {vm.updatePassword(it)},
                    isEnable = uiData.value.email.isNotEmpty() && uiData.value.password.length>=6
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

