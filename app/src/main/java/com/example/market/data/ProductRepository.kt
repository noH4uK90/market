package com.example.market.data

import com.example.market.dto.ProductDto
import com.example.market.network.ProductApiService

interface ProductRepository {

    suspend fun getProducts() : List<ProductDto>

    suspend fun getProduct(id: Int): ProductDto

    suspend fun deleteProduct(id: Int)
}

class NetworkProductRepository(
    private val productApiService: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): List<ProductDto> = productApiService.getProducts()

    override suspend fun getProduct(id: Int): ProductDto = productApiService.getProduct(id)

    override suspend fun deleteProduct(id: Int) = productApiService.deleteProduct(id)
}