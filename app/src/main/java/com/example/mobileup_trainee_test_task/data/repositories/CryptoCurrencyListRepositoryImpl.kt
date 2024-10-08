package com.example.mobileup_trainee_test_task.data.repositories

import com.example.mobileup_trainee_test_task.common.Constants
import com.example.mobileup_trainee_test_task.data.network.dto.CryptoCurrencyDto
import com.example.mobileup_trainee_test_task.data.remote.retrofit_services.CryptoCurrencyListService
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository
import retrofit2.Retrofit


class CryptoCurrencyListRepositoryImpl (
    retrofit: Retrofit
) : CryptoCurrencyListRepository {


    private val service: CryptoCurrencyListService =
        retrofit.create(CryptoCurrencyListService::class.java)

    override suspend fun getCryptoCurrencyList(currency: String): List<CryptoCurrencyDto> {
       return service.getCryptoCurrencyList(currency, Constants.CRYPTO_AMOUNT)
    }
}