package com.lc.robot;

import android.app.Application
import timber.log.Timber

class RobotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
