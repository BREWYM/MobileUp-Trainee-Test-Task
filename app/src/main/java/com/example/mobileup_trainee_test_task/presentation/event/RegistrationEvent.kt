package com.example.mobileup_trainee_test_task.presentation.event

sealed class RegistrationEvent {
    data class EmailChanged(val email: String) : RegistrationEvent()
    data class PasswordChanged(val password: String) : RegistrationEvent()
    data class ConfirmPasswordChanged(val password: String) : RegistrationEvent()
    object Submit : RegistrationEvent()
    object ResetNavigation : RegistrationEvent()
}