package com.example.mobileup_trainee_test_task.domain.repositories

import com.example.mobileup_trainee_test_task.data.network.dto.CryptoDescriptionDto


interface CryptoDescriptionRepository {

    suspend fun getCryptoDescription(id: String): CryptoDescriptionDto
}