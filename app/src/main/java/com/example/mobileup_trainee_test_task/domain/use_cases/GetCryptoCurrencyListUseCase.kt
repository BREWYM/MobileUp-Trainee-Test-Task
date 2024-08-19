package com.example.mobileup_trainee_test_task.domain.use_cases

import com.example.mobileup_trainee_test_task.common.Resource
import com.example.mobileup_trainee_test_task.data.network.dto.toCryptoCurrency
import com.example.mobileup_trainee_test_task.domain.models.CryptoCurrency
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCryptoCurrencyListUseCase(
    private val cryptoCurrencyListRepository: CryptoCurrencyListRepository
) {

    suspend operator fun invoke(currency: String): Flow<Resource<List<CryptoCurrency>>> = flow {
        try {
            emit(Resource.Loading())
            val cryptoCurrency = cryptoCurrencyListRepository.getCryptoCurrencyList(currency).map {
                it.toCryptoCurrency()

            }
           cryptoCurrency.forEach{ println(it)}
            emit(Resource.Success(cryptoCurrency))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected HTTP error occurred"
                )
            )

        } catch (e: IOException) {
            emit(Resource.Error("An internet error occurred. Please check your connection "))
        }
    }
}