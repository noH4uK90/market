package com.example.market.ui.screens

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthorizeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthorizationViewModel
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
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

                OutlinedButton(
                    onClick = {
                        viewModel.viewModelScope.launch {
                            val id = async(Dispatchers.IO) {
                                viewModel.authorizeUser(login, password)
                            }.await()
                            if (id != 0) navController.navigate("ProductScreen")
                            else {
                                viewModel.viewModelScope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Неверный логин или пароль"
                                    )
                                }
                            }
                        }
                    },
                    modifier = modifier
                        .width(200.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Авторизоваться")
                }

                OutlinedButton(
                    onClick = {},
                    modifier = modifier
                        .width(200.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Регситрация")
                }
            }
        }
    }

}