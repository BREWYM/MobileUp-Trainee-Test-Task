package com.example.mobileup_trainee_test_task.data.repositories

import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository

private const val BASE_URL = "https://api.coingecko.com/api/v3/"
class CryptoDescriptionRepositoryImpl: CryptoDescriptionRepository {


    override suspend fun getCryptoDescription(id: String): CryptoDescription {
        TODO("Not yet implemented")
    }
}