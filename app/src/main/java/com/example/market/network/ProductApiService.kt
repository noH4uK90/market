package com.example.market.network

import com.example.market.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("Product")
    suspend fun getProducts(): List<ProductDto>

    @GET("Product/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDto

    @GET("Delete/{id}")
    suspend fun deleteProduct(@Path("id") id: Int)
}