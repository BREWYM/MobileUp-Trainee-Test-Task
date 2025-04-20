package com.example.mobileup_trainee_test_task.domain.use_cases

import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val isSuccess = authRepository.register(email, password)
            emit(Resource.Success(isSuccess))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Registration failed"))
        }
    }
}