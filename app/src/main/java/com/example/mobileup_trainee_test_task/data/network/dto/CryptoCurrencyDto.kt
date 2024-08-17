package com.example.mobileup_trainee_test_task.data.network.dto


import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency
import com.google.gson.annotations.SerializedName

data class CryptoCurrencyDto(
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: Double,
    @SerializedName("symbol")
    val symbol: String,
    )

fun CryptoCurrencyDto.toCryptoCurrency(): CryptoCurrency {
    return CryptoCurrency(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage
    )
}