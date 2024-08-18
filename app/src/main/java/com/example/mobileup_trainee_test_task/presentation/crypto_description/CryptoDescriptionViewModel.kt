package com.example.mobileup_trainee_test_task.presentation.crypto_description

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileup_trainee_test_task.common.Constants
import com.example.mobileup_trainee_test_task.common.Currency
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoDescriptionUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CryptoDescriptionViewModel(
    private val getCryptoCurrencyDescriptionUseCase: GetCryptoDescriptionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CryptoDescriptionState>(
        CryptoDescriptionState()
    )
    val state: State<CryptoDescriptionState> = _state

    private val _currency = mutableStateOf<Currency>(Currency.USD)
    val currency: State<Currency> = _currency

    init {
        savedStateHandle.get<String>(Constants.PARAM_CRYPTO_ID)?.let { cryptoId ->
            viewModelScope.launch {  getCryptoCurrency(cryptoId)}
        }
    }
    suspend fun refresh(cryptoId: String){
        getCryptoCurrency(cryptoId = cryptoId)
    }
     private suspend fun getCryptoCurrency(cryptoId: String) {
        getCryptoCurrencyDescriptionUseCase(cryptoId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoDescriptionState(crypto = result.data)
                }

                is Resource.Error -> {
                    _state.value = CryptoDescriptionState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CryptoDescriptionState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}