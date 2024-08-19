package com.example.mobileup_trainee_test_task.domain.models

data class CryptoDescription(
    val description: String,
    val categories: List<String>,
    val name: String,
    val image: String,
    val cryptoId: String
)
