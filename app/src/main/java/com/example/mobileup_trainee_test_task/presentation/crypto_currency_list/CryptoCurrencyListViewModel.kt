package com.example.mobileup_trainee_test_task.presentation.crypto_currency_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileup_trainee_test_task.common.Currency
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency
import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoCurrencyListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CryptoCurrencyListViewModel(
    private val getCryptoCurrencyListUseCase: GetCryptoCurrencyListUseCase
) : ViewModel() {

    private val _isRefreshing = mutableStateOf(false)
    var isRefreshing: State<Boolean> = _isRefreshing

    private val _state = mutableStateOf(CryptoCurrencyListState())
    val state: State<CryptoCurrencyListState> = _state

    private var _currency = mutableStateOf(Currency.USD)
    val currency: State<Currency> = _currency

    private var _lastSucceededData = mutableStateOf(emptyList<CryptoCurrency>())
    val lastSucceededData = _lastSucceededData

    private val _refreshError = mutableStateOf(false)
    val refreshError: State<Boolean> = _refreshError

    init {
        viewModelScope.launch { getCryptoCurrencies() }
    }

    suspend fun refreshData() {
        _isRefreshing.value = true
        getCryptoCurrencies()
    }

    fun getCryptoByCurrency(currency: Currency) {
        _currency.value = currency
        _lastSucceededData.value = emptyList()
        viewModelScope.launch { getCryptoCurrencies() }
    }

    private suspend fun getCryptoCurrencies() {
        getCryptoCurrencyListUseCase(_currency.value.id).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoCurrencyListState(cryptos = result.data ?: emptyList())
                    _lastSucceededData.value = _state.value.cryptos
                    _isRefreshing.value = false
                }

                is Resource.Error -> {
                    if (!_isRefreshing.value) {

                        _state.value = CryptoCurrencyListState(
                            error = result.message ?: "An unexpected error occurred"

                        )
                        _lastSucceededData.value = emptyList()
                    } else {
                        _state.value = CryptoCurrencyListState(
                            afterRefresh = true,
                            error = result.message ?: "An unexpected error occurred after refresh"
                        )
                        _refreshError.value = true

                        _isRefreshing.value = false
                    }

                }

                is Resource.Loading -> {
                    _refreshError.value = false
                    _state.value = CryptoCurrencyListState(
                        isLoading = true,
                        cryptos = _lastSucceededData.value
                    )

                }
            }
        }.launchIn(viewModelScope)
    }
}