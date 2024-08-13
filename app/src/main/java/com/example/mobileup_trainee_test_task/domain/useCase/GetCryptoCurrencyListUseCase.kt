package com.example.mobileup_trainee_test_task.domain.useCase

import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository

class GetCryptoCurrencyListUseCase(
    private val cryptoCurrencyListRepository: CryptoCurrencyListRepository
) {

    suspend fun execute(currency: String) {
        cryptoCurrencyListRepository.getCryptoCurrencyList(currency)
    }
}