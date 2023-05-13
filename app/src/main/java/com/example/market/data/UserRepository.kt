package com.example.market.data

import com.example.market.dto.UserDto
import com.example.market.models.User
import com.example.market.network.UserApiService
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): List<UserDto>

    suspend fun getUser(id: Int): UserDto

    suspend fun authorizeUser(login: String, password: String): Response<Int>

    suspend fun addUser(user: User)
}

class NetworkUserRepository(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun getUsers(): List<UserDto> = userApiService.getUsers()

    override suspend fun getUser(id: Int): UserDto = userApiService.getUser(id)

    override suspend fun authorizeUser(login: String, password: String): Response<Int> = userApiService.authorizeUser(login, password)

    override suspend fun addUser(user: User) = userApiService.addUser(user)
}