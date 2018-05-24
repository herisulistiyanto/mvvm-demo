package com.andro.indieschool.mymvvmdemo

import android.os.StrictMode
import com.andro.indieschool.mymvvmdemo.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject


class MyApp : DaggerApplication() {

    @Inject
    lateinit var loggingTree: Timber.Tree

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupDebugTools()
    }

    private fun setupDebugTools() {
        initStrictMode()
        initTimber()
    }

    private fun initStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .penaltyDeathOnNetwork()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
    }

    private fun initTimber() {
        Timber.plant(loggingTree)
    }
}