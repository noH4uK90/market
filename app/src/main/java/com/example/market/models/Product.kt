package com.example.market.models

data class Product(
    val article: String,
    val categoryId: Int,
    val count: Int,
    val currentDiscount: Int,
    val description: String,
    val image: String?,
    val maxDiscount: Int,
    val nameId: Int,
    val price: Int,
    val producerId: Int,
    val unit: String,
    val vendorId: Int
)
