package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileup_trainee_test_task.common.Currency
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoCurrencyListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CryptoCurrencyListViewModel(
    private val getCryptoCurrencyListUseCase: GetCryptoCurrencyListUseCase
) : ViewModel() {

    private val _state = mutableStateOf<CryptoCurrencyListState>(CryptoCurrencyListState())
    val state: State<CryptoCurrencyListState> = _state

    private var _currency = mutableStateOf<Currency>(Currency.USD)
    val currency: State<Currency> = _currency

    init {
        viewModelScope.launch { getCryptoCurrencies() }
        println("I am CryptoCurrencyListVM and I'am alive")
    }

     fun selectedCurrencyChange(currency: Currency){
        _currency.value = currency
         viewModelScope.launch { getCryptoCurrencies() }
    }
     private suspend fun getCryptoCurrencies() {
        getCryptoCurrencyListUseCase(_currency.value.id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoCurrencyListState(cryptos = result.data?: emptyList())
                }

                is Resource.Error -> {
                    println("Hi, ERROR")

                    _state.value = CryptoCurrencyListState(
                        error = result.message ?: "An unexpected error occurred"

                    )
                }

                is Resource.Loading -> {
                    println("Hi, LOADING")
                    _state.value = CryptoCurrencyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}