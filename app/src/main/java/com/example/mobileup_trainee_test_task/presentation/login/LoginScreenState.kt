package com.example.mobileup_trainee_test_task.presentation.login

import android.util.Patterns
import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.presentation.Screen

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val authError: String? = null,
    val shouldNavigate: Screen? = null
)

sealed class NavigationEvent {
    object NavigateToMain : NavigationEvent()
    object NavigateToRegistration : NavigationEvent()
}