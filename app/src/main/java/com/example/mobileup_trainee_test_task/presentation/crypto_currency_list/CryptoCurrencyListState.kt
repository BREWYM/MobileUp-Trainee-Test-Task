package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency

data class CryptoCurrencyListState (
    val isLoading: Boolean = false,
//    val cryptos: List<CryptoCurrency> = listOf(CryptoCurrency("bitcoin", "btc","Bitcoin",
//        "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400", 70817,2126.88)),
    val cryptos : List<CryptoCurrency> = emptyList<CryptoCurrency>(),
    val error: String = ""
)