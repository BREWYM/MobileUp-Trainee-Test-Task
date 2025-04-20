package com.example.mobileup_trainee_test_task.presentation.registration

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.use_cases.RegisterUseCase
import com.example.mobileup_trainee_test_task.presentation.Screen
import com.example.mobileup_trainee_test_task.presentation.event.RegistrationEvent
import kotlinx.coroutines.launch


class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RegistrationState())
    val state: State<RegistrationState> = _state

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.EmailChanged -> updateEmail(event.email)
            is RegistrationEvent.PasswordChanged -> updatePassword(event.password)
            is RegistrationEvent.ConfirmPasswordChanged -> updateConfirmPassword(event.password)
            RegistrationEvent.Submit -> performRegistration()
            RegistrationEvent.ResetNavigation -> {
                _state.value = _state.value.copy(shouldNavigate = null)
            }
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

    private fun updateConfirmPassword(password: String) {
        _state.value = _state.value.copy(
            confirmPassword = password,
            confirmPasswordError = null,
            authError = null
        )
    }

    private fun performRegistration() {
        val emailError = validateEmail()
        val passwordError = validatePassword()
        val confirmError = validateConfirmPassword()

        if (emailError != null || passwordError != null || confirmError != null) {
            _state.value = _state.value.copy(
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmError
            )
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            registerUseCase(_state.value.email, _state.value.password)
                .collect { result ->
                    _state.value = when (result) {
                        is Resource.Success -> {
                            if (result.data == true) {
                                _state.value.copy(
                                    isLoading = false,
                                    shouldNavigate = Screen.LoginScreen
                                )
                            } else {
                                _state.value.copy(
                                    isLoading = false,
                                    authError = "Registration failed (user may already exist)"
                                )
                            }
                        }
                        is Resource.Error -> _state.value.copy(
                            isLoading = false,
                            authError = result.message ?: "Registration failed"
                        )
                        is Resource.Loading -> _state.value.copy(isLoading = true)
                    }
                }
        }
    }

    private fun validateEmail(): String? {
        return when {
            _state.value.email.isBlank() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches() -> "Invalid email format"
            else -> null
        }
    }

    private fun validatePassword(): String? {
        return when {
            _state.value.password.isBlank() -> "Password is required"
            _state.value.password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    private fun validateConfirmPassword(): String? {
        return if (_state.value.password != _state.value.confirmPassword) {
            "Passwords don't match"
        } else {
            null
        }
    }
}