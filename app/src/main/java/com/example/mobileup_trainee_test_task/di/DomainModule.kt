package com.example.mobileup_trainee_test_task.di

import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoCurrencyListUseCase
import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoDescriptionUseCase
import com.example.mobileup_trainee_test_task.domain.use_cases.LoginUseCase
import com.example.mobileup_trainee_test_task.domain.use_cases.RegisterUseCase
import org.koin.dsl.module

val domainModule = module{

    factory {
        GetCryptoCurrencyListUseCase(
            cryptoCurrencyListRepository = get()
        )
    }
    factory {
        GetCryptoDescriptionUseCase(
            cryptoDescriptionRepository = get()
        )
    }
    factory {
        LoginUseCase(
            authRepository = get()
        )
    }
    factory { RegisterUseCase(get()) }
}