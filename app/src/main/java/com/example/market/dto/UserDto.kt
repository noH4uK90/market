package com.example.market.dto

import com.example.market.models.Role
import kotlinx.serialization.SerialName

data class UserDto(
    @SerialName("userId")
    val id: Int,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val login: String,
    val roleId: Int,
    val role: Role
)