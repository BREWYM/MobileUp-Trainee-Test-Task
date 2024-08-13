package com.example.mobileup_trainee_test_task.domain.useCase

import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository

class GetCryptoDescriptionUseCase(
    private val cryptoDescriptionRepository: CryptoDescriptionRepository
) {
    suspend fun execute(id: String): CryptoDescription{
        return cryptoDescriptionRepository.getCryptoDescription(id)
    }
}