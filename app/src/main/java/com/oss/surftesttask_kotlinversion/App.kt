package com.oss.surftesttask_kotlinversion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
    }
}
