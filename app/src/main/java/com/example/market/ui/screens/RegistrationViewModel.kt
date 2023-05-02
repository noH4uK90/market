package com.example.market.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.market.MainApplication
import com.example.market.data.UserRepository
import com.example.market.dto.UserDto
import com.example.market.models.User

class RegistrationViewModel(private val userRepository: UserRepository): ViewModel() {

    suspend fun registerUser(login: String, password: String, confirmPassword: String, firstName: String, lastName: String, middleName: String): String {
        if (!checkPassword(password, confirmPassword))
            return "Пароли должны совпадать"

        val users = userRepository.getUsers()
        if (checkUser(users, login))
            return "Пользователь с таким логином уже существует"

        val user = User(firstName, middleName, lastName, login, password, 1)
        return "Вы успешно зарегистрировались"
    }

    private fun checkPassword(password: String, confirmPassword: String): Boolean = password == confirmPassword

    private fun checkUser(users: List<UserDto>, login: String): Boolean = users.any { user -> user.login == login }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val userRepository = application.container.userRepository
                RegistrationViewModel(userRepository)
            }
        }
    }
}