package com.example.mobileup_trainee_test_task.presentation.registration

import com.example.mobileup_trainee_test_task.presentation.Screen

data class RegistrationState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val authError: String? = null,
    val shouldNavigate: Screen? = null
)