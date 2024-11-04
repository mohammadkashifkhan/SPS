package com.mdkashif.spsol.base

import android.app.Application
import com.mdkashif.spsol.shared.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

            startKoin {
                androidLogger()
                androidContext(this@BaseApplication)
                modules(listOf(appModule))
            }
    }
}