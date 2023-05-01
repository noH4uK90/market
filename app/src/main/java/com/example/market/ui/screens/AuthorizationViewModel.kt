package com.example.market.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.market.MainApplication
import com.example.market.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class AuthorizationViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id


    suspend fun authorizeUser(login: String, password: String): Int {
        val result = withContext(Dispatchers.IO) {
            userRepository.authorizeUser(login, password)
        }
        return result
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val userRepository = application.container.userRepository
                AuthorizationViewModel(userRepository)
            }
        }
    }
}