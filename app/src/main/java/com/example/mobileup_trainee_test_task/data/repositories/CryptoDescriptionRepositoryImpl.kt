package com.example.mobileup_trainee_test_task.data.repositories

import com.example.mobileup_trainee_test_task.data.network.dto.CryptoDescriptionDto
import com.example.mobileup_trainee_test_task.data.remote.retrofit_services.CryptoDescriptionService
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository
import retrofit2.Retrofit
import java.io.IOException

class CryptoDescriptionRepositoryImpl(
    retrofit: Retrofit
):  CryptoDescriptionRepository
{

    private val service: CryptoDescriptionService =
        retrofit.create(CryptoDescriptionService::class.java)

    override suspend fun getCryptoDescription(id: String): CryptoDescriptionDto {
        return service.getCryptoDescription(id)
    }
}