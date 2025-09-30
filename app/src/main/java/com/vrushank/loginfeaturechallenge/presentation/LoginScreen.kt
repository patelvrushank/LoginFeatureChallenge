package com.vrushank.loginfeaturechallenge.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    emailValue: String,
    onChangeEmail: (String) -> Unit,
    passValue: String,
    onChangePass: (String) -> Unit,
    isEnable: Boolean,
    isLoading: Boolean,
    loginOnClick: () -> Unit,
    errorMsg: String?
) {
    val context = LocalContext.current
    LaunchedEffect(errorMsg) {
        errorMsg.let { Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show() }
    }
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .height(20.dp)
                .background(Color.Transparent)
        ) {
            CircularProgressIndicator()
        }
    }






    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = emailValue,
            onValueChange = { onChangeEmail(it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = passValue,
            onValueChange = { onChangePass(it) },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )

        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                "Forgot Password?",
                style = LocalTextStyle.current.copy(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
        /*

                ClickableText(
                    text = AnnotatedString("Forgot Password"),
                    style = LocalTextStyle.current.copy(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.align(Alignment.End).padding(end =20.dp),
                    onClick = {})
        */

        Button(
            onClick = { loginOnClick() },
            enabled = isEnable,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) { Text("Login") }

    }
}