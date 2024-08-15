package com.example.mobileup_trainee_test_task.domain.repositories

import com.example.mobileup_trainee_test_task.data.network.dto.CryptoCurrencyDto

interface CryptoCurrencyListRepository {

    suspend fun getCryptoCurrencyList(currency: String): List<CryptoCurrencyDto>
}