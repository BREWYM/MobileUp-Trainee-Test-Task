package com.example.mobileup_trainee_test_task.domain.repositories

import com.example.mobileup_trainee_test_task.domain.models.Cryptocurrency

interface CryptoCurrencyListRepository {

    suspend fun getCryptoCurrencyList(currency: String): List<Cryptocurrency>
}