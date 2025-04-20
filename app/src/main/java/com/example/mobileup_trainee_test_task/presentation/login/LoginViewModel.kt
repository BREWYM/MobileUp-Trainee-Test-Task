package com.example.mobileup_trainee_test_task.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.use_cases.LoginUseCase
import com.example.mobileup_trainee_test_task.presentation.Screen
import com.example.mobileup_trainee_test_task.presentation.event.LoginEvent
import kotlinx.coroutines.launch

// presentation/login/LoginViewModel.kt
class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state: State<LoginScreenState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> updateEmail(event.email)
            is LoginEvent.PasswordChanged -> updatePassword(event.password)
            LoginEvent.Submit -> performLogin()
        }
    }

    private fun updateEmail(email: String) {
        _state.value = _state.value.copy(
            email = email,
            emailError = null,
            authError = null
        )
    }

    private fun updatePassword(password: String) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = null,
            authError = null
        )
    }

    private fun performLogin() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            loginUseCase(_state.value.email, _state.value.password)
                .collect { result ->
                    _state.value = when (result) {
                        is Resource.Success -> {
                            if (result.data == true) {
                                _state.value.copy(
                                    isLoading = false,
                                    shouldNavigate = Screen.CryptoCurrencyListScreen
                                )
                            } else {
                                _state.value.copy(
                                    isLoading = false,
                                    authError = "Invalid email or password"
                                )
                            }
                        }
                        is Resource.Error -> _state.value.copy(
                            isLoading = false,
                            authError = result.message ?: "Login failed"
                        )
                        is Resource.Loading -> _state.value.copy(isLoading = true)
                    }
                }
        }
    }
}