package com.example.market.network

import com.example.market.dto.UserDto
import com.example.market.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @GET("User")
    suspend fun getUsers(): List<UserDto>

    @GET("User/{id}")
    suspend fun getUser(@Path("id") id: Int): UserDto

    @GET("User/authorize")
    suspend fun authorizeUser(@Query("login") login: String, @Query("password") password: String): Response<Int>

    @POST("User")
    suspend fun addUser(@Body user:User)
}