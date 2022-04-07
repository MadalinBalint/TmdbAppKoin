package com.mendelin.tmdb_koin.base

import android.app.Application
import com.mendelin.tmdb_koin.data.logging.TimberPlant
import com.mendelin.tmdb_koin.di.appComponents
import com.mendelin.tmdb_koin.di.networkComponents
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TmdbApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        TimberPlant.plantTimberDebugLogger()

        startKoin {
            androidLogger()
            androidContext(this@TmdbApplication)
            modules(appComponents + networkComponents)
        }
    }
}