package com.example.mobileup_trainee_test_task.di

import com.example.mobileup_trainee_test_task.domain.use_cases.GetCryptoDescriptionUseCase
import org.koin.dsl.module

val domainModule = module{

    factory {
        GetCryptoDescriptionUseCase(
            repository = get()
        )
    }
    factory {
        GetCryptoDescriptionUseCase(
            repository = get()
        )
    }
}