package com.example.market.models

data class User(
    val firstName: String,
    val middlename: String,
    val lastname: String,
    val login: String,
    val password: String,
    val roleId: Int
)
