package com.example.mobileup_trainee_test_task.data.network.dto


import com.google.gson.annotations.SerializedName

data class ConvertedVolume(
    @SerializedName("btc")
    val btc: Int,
    @SerializedName("eth")
    val eth: Int,
    @SerializedName("usd")
    val usd: Int
)