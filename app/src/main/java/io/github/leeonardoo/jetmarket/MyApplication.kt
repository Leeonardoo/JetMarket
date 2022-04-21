package io.github.leeonardoo.jetmarket

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val imageLoader = ImageLoader.Builder(applicationContext).apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }.build()

        Coil.setImageLoader(imageLoader)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}