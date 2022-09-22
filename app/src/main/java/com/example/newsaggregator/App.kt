package com.example.newsaggregator

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsaggregator.feature.description_screen.descriptionScreenModule
import com.example.newsaggregator.feature.di.mainScreenModule
import com.example.newsaggregator.di.databaseModule
import com.example.newsaggregator.di.networkModule
import com.example.newsaggregator.feature.bookmarks.di.bookmarksModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                mainScreenModule,
                databaseModule,
                bookmarksModule,
                descriptionScreenModule
            )
            // Использование системной темы в зависимости от настроек пользователя(день-ночь)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}