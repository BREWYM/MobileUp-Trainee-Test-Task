package com.example.mobileup_trainee_test_task.di

import com.example.mobileup_trainee_test_task.domain.use_cases.LoginUseCase
import com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.CryptoCurrencyListViewModel
import com.example.mobileup_trainee_test_task.presentation.crypto_description.CryptoDescriptionViewModel
import com.example.mobileup_trainee_test_task.presentation.login.LoginViewModel
import com.example.mobileup_trainee_test_task.presentation.registration.RegistrationScreen
import com.example.mobileup_trainee_test_task.presentation.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CryptoCurrencyListViewModel(getCryptoCurrencyListUseCase = get()) }
    viewModel { CryptoDescriptionViewModel(getCryptoCurrencyDescriptionUseCase = get(), savedStateHandle = get()) }
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel{ RegistrationViewModel(registerUseCase = get()) }

}