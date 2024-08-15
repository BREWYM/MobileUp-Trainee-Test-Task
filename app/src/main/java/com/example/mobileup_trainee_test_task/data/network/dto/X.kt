package com.example.mobileup_trainee_test_task.data.network.dto


import com.google.gson.annotations.SerializedName

data class X(
    @SerializedName("contract_address")
    val contractAddress: String,
    @SerializedName("decimal_place")
    val decimalPlace: Any
)