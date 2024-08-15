package com.example.mobileup_trainee_test_task.data.network.dto


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("de")
    val de: String,
    @SerializedName("en")
    val en: String
)