package com.example.mobileup_trainee_test_task.presentation.crypto_description

import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription

data class CryptoDescriptionState (
    val isLoading: Boolean = false,
    val crypto: CryptoDescription ?= null,
    val error: String = ""
)