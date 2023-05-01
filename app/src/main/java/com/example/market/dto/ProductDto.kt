package com.example.market.dto

import com.example.market.models.Category
import com.example.market.models.Name
import com.example.market.models.Producer
import com.example.market.models.Vendor
import kotlinx.serialization.SerialName

data class ProductDto(
    @SerialName("productId")
    val id: Int,
    val article: String,
    val category: Category,
    val categoryId: Int,
    val count: Int,
    val currentDiscount: Int,
    val description: String,
    val image: String?,
    val maxDiscount: Int,
    val name: Name,
    val nameId: Int,
    val price: Int,
    val producer: Producer,
    val producerId: Int,
    val unit: String,
    val vendor: Vendor,
    val vendorId: Int
)