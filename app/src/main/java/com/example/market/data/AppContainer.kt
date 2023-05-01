package com.example.market.data

import com.example.market.network.ProductApiService
import com.example.market.network.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val userRepository: UserRepository

    val productRepository: ProductRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://sirevim211.bsite.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val userService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    private val productService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(userService)
    }

    override val productRepository: ProductRepository by lazy {
        NetworkProductRepository(productService)
    }
}