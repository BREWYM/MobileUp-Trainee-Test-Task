package com.example.mobileup_trainee_test_task.domain.use_cases

import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.data.network.dto.toCryptoDescription
import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCryptoDescriptionUseCase(
    private val cryptoDescriptionRepository: CryptoDescriptionRepository
) {
    suspend operator fun invoke(id: String): Flow<Resource<CryptoDescription>> = flow{
        try {
            emit(Resource.Loading())
            val cryptoDescription = cryptoDescriptionRepository.getCryptoDescription(id = id).toCryptoDescription()
            emit(Resource.Success<CryptoDescription>(cryptoDescription))

        } catch (e: HttpException){
            emit(Resource.Error<CryptoDescription>(e.localizedMessage?: "An unexpected HTTP error occurred"))

        } catch (e: IOException){
            emit(Resource.Error<CryptoDescription>("An internet error occurred. Please check your connection "))
        }
    }
}