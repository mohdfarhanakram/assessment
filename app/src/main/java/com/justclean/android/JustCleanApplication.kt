package com.justclean.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@HiltAndroidApp
class JustCleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}