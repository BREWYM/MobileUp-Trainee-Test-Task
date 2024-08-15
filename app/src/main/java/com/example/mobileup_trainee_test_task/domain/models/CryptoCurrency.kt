package com.example.mobileup_trainee_test_task.domain.models


data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Int,
    val priceChange24h: Double,
    ) {
}

