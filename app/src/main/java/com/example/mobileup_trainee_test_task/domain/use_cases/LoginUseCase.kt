package com.example.mobileup_trainee_test_task.domain.use_cases

import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.data.repositories.AuthRepositoryImpl
import com.example.mobileup_trainee_test_task.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginUseCase(
    private val authRepository : AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val isSuccess = authRepository.login(email, password)
            emit(Resource.Success(isSuccess))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected HTTP error occurred. Auth failed"
                )
            )

        } catch (e: IOException) {
            emit(Resource.Error("An internet error occurred. Auth failed. Please check your connection "))
        }
    }
}