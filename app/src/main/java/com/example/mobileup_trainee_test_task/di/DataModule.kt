package com.example.mobileup_trainee_test_task.di
import com.example.mobileup_trainee_test_task.common.Constants
import com.example.mobileup_trainee_test_task.data.repositories.CryptoCurrencyListRepositoryImpl
import com.example.mobileup_trainee_test_task.data.repositories.CryptoDescriptionRepositoryImpl
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val datamodule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    single<CryptoCurrencyListRepository>{
        CryptoCurrencyListRepositoryImpl(retrofit = get())
    }
    single<CryptoDescriptionRepository>{
        CryptoDescriptionRepositoryImpl(retrofit = get())
    }
}