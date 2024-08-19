package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency

data class CryptoCurrencyListState (
    val isLoading: Boolean = false,
    val cryptos : List<CryptoCurrency> = emptyList(),
    val error: String = "",
    val afterRefresh: Boolean = false
)