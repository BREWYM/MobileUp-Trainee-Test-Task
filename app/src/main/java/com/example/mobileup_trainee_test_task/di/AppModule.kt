package com.example.mobileup_trainee_test_task.di

import com.example.mobileup_trainee_test_task.presentation.crypto_currency_list.CryptoCurrencyListViewModel
import com.example.mobileup_trainee_test_task.presentation.crypto_description.CryptoDescriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CryptoCurrencyListViewModel(getCryptoCurrencyListUseCase = get()) }
    viewModel { CryptoDescriptionViewModel(getCryptoCurrencyDescriptionUseCase = get(), savedStateHandle = get()) }

}