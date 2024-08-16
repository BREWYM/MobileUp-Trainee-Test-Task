package com.example.mobileup_trainee_test_task

import android.app.Application
import com.example.mobileup_trainee_test_task.di.appModule
import com.example.mobileup_trainee_test_task.di.datamodule
import com.example.mobileup_trainee_test_task.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CryptoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CryptoApplication)
            modules(appModule, domainModule, datamodule)
        }
    }
}