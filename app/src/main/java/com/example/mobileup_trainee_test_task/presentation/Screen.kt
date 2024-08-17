package com.example.mobileup_trainee_test_task.presentation

sealed class Screen(val route: String) {
    object CryptoCurrencyListScreen: Screen("crypto_currency_list_screen")
    object CryptoDescriptionScreen: Screen("crypto_description_screen")
}