package com.example.mobileup_trainee_test_task.data.network.dto


import com.google.gson.annotations.SerializedName

data class CodeAdditionsDeletions4Weeks(
    @SerializedName("additions")
    val additions: Int,
    @SerializedName("deletions")
    val deletions: Int
)