package com.example.market.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RegistrationViewModel
) {

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text(text = "Логин") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Пароль") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Подтверждение пароля") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "Имя") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Фамилия") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedTextField(
                value = middleName,
                onValueChange = { middleName = it },
                label = { Text(text = "Отчество") },
                shape = RoundedCornerShape(20),
                singleLine = true,
            )

            OutlinedButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        val message = viewModel.registerUser(login, password, confirmPassword, firstName, lastName, middleName)
                        snackbarHostState.showSnackbar(message)
                    }
                }
            ) {
                Text(text = "Зарегестрироваться")
            }

            OutlinedButton(
                onClick = {
                    navController.navigate("AuthorizationScreen")
                }
            ) {
                Text(text = "Войти")
            }
        }
    }
}