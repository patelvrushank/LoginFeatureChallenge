package com.vrushank.loginfeaturechallenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    emailValue: String,
    onChangeEmail: (String) -> Unit,
    passValue: String,
    onChangePass: (String) -> Unit,
    isEnable: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value =emailValue,
            onValueChange ={ onChangeEmail(it)},
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = passValue,
            onValueChange = {onChangePass(it)},
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {},
            enabled = isEnable,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) { Text("Login") }

    }
}