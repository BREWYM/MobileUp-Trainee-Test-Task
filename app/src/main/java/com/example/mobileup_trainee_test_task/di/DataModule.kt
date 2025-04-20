package com.example.mobileup_trainee_test_task.di
import com.example.mobileup_trainee_test_task.common.Constants
import com.example.mobileup_trainee_test_task.data.network.AuthService
import com.example.mobileup_trainee_test_task.data.network.FirebaseAuthService
import com.example.mobileup_trainee_test_task.data.repositories.AuthRepositoryImpl
import com.example.mobileup_trainee_test_task.data.repositories.CryptoCurrencyListRepositoryImpl
import com.example.mobileup_trainee_test_task.data.repositories.CryptoDescriptionRepositoryImpl
import com.example.mobileup_trainee_test_task.domain.repositories.AuthRepository
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoCurrencyListRepository
import com.example.mobileup_trainee_test_task.domain.repositories.CryptoDescriptionRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CryptoCurrencyListRepository> {
        CryptoCurrencyListRepositoryImpl(retrofit = get())
    }

    single<CryptoDescriptionRepository> {
        CryptoDescriptionRepositoryImpl(retrofit = get())
    }

    // Для Firebase Auth
    single<FirebaseAuth> { Firebase.auth }

    // Явно указываем тип для AuthRepositoryImpl
    single<AuthRepository> {
        AuthRepositoryImpl(firebaseAuth = get<FirebaseAuth>())
    }
}