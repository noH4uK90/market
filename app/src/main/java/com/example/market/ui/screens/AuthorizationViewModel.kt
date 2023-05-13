package com.example.market.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.market.MainApplication
import com.example.market.data.UserRepository
import com.example.market.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id

    init {
        addUser()
    }

    suspend fun authorizeUser(login: String, password: String): Int {
        val result = withContext(Dispatchers.IO) {
            userRepository.authorizeUser(login, password)
        }
        return result.body() ?: 0
    }

    fun addUser() {
        viewModelScope.launch {
            var user = User("Name", "MiddleName", "Surname", "newTestLogin", "pas", 1)
            withContext(Dispatchers.IO) {
                userRepository.addUser(user)
            }
        }
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