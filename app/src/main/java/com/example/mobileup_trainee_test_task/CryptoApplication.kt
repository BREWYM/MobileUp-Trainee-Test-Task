package com.example.mobileup_trainee_test_task

import android.app.Application
import com.example.mobileup_trainee_test_task.di.appModule
import com.example.mobileup_trainee_test_task.di.dataModule

import com.example.mobileup_trainee_test_task.di.domainModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CryptoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin{
            androidContext(this@CryptoApplication)
            modules(appModule, domainModule, dataModule)
        }
    }
}