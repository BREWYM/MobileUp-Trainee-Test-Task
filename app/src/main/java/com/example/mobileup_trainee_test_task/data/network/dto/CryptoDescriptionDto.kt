package com.example.mobileup_trainee_test_task.data.network.dto


import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription
import com.google.gson.annotations.SerializedName

data class CryptoDescriptionDto(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("description")
    val description: Description,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

)

fun CryptoDescriptionDto.toCryptoDescription() : CryptoDescription{
    return CryptoDescription(
        description = description.en,
        categories = categories,
        id = id,
        symbol= symbol
    )
}