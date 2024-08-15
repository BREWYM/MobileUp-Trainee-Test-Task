package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import androidx.lifecycle.ViewModel
import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoCurrencyListUseCase

class CryptoCurrencyListViewModel(
    private val getCryptoCurrencyListUseCase: GetCryptoCurrencyListUseCase
): ViewModel() {
}