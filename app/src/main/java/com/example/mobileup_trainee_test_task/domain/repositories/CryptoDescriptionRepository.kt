package com.example.mobileup_trainee_test_task.domain.repositories

import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription

interface CryptoDescriptionRepository {

    suspend fun getCryptoDescription(id: String): CryptoDescription
}