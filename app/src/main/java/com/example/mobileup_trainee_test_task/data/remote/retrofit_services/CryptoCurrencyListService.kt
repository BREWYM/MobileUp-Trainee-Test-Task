package com.example.mobileup_trainee_test_task.data.remote.retrofit_services

import com.example.mobileup_trainee_test_task.data.network.dto.CryptoCurrencyDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyListService {

    @GET("coins/markets")
    suspend fun getCryptoCurrencyList(
        @Query("vs_currency")
        vsCurrency: String,
        @Query("per_page")
        perPage: Int
    ): List<CryptoCurrencyDto>
}