package com.example.market.models

data class User(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val login: String,
    val password: String,
    val roleId: Int
)
