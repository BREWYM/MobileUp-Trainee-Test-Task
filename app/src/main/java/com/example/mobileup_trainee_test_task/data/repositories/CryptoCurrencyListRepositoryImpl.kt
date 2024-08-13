package com.example.mobileup_trainee_test_task.data.repositories

import com.example.mobileup_trainee_test_task.data.network.retrofitServices.CryptoCurrencyListService
import com.example.mobileup_trainee_test_task.domain.models.Cryptocurrency
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository
import retrofit2.Retrofit

private const val BASE_URL = "https://api.coingecko.com/api/v3/"
class CryptoCurrencyListRepositoryImpl(
    private val retrofit: Retrofit
) : CryptoCurrencyListRepository {

    private val service: CryptoCurrencyListService =
        retrofit.create(CryptoCurrencyListService::class.java)

    override suspend fun getCryptoCurrencyList(currency: String): List<Cryptocurrency> {
        //Что-то надо будет тут придумать с Null-ом
       return service.getCryptoCurrencyList(currency).execute().body()!!
    }
}