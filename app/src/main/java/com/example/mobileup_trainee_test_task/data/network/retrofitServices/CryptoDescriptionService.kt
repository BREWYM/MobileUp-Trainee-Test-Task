package com.example.mobileup_trainee_test_task.data.network.retrofitServices

import com.example.mobileup_trainee_test_task.domain.models.CryptoDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoDescriptionService {

    @GET("coins/{id}")
    suspend fun getCryptoDescription(
        @Path("id")
        id: String,
        @Query("localization")
        localization: Boolean = false
    ): Call<CryptoDescription>
}