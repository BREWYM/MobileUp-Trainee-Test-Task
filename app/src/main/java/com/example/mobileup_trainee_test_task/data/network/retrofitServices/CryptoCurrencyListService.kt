package com.example.mobileup_trainee_test_task.data.network.retrofitServices

import com.example.mobileup_trainee_test_task.domain.models.Cryptocurrency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyListService {

    @GET("coins/markets")
    suspend fun getCryptoCurrencyList(
        @Query("vs_currency")
        vsCurrency: String
    ): Call<List<Cryptocurrency>>
}