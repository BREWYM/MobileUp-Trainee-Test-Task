package com.example.mobileup_trainee_test_task.presentation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object RegistrationScreen: Screen("registration_screen")
    object CryptoCurrencyListScreen: Screen("crypto_currency_list_screen")
    object CryptoDescriptionScreen: Screen("crypto_description_screen")
    object AuthCheckScreen : Screen("auth_check")
}