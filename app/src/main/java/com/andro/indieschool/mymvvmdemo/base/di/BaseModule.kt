package com.andro.indieschool.mymvvmdemo.base.di

import android.app.Application
import android.content.Context
import com.andro.indieschool.mymvvmdemo.base.di.qualifiers.ForApplication
import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerApplication
import com.andro.indieschool.mymvvmdemo.base.ui.adapter.DiffCallback
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class BaseModule {

    @Provides
    @PerApplication
    @ForApplication
    fun providesContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun providesLogger(): Timber.Tree = Timber.DebugTree()

    @Provides
    @PerApplication
    fun providesDiffCallback(): DiffCallback = DiffCallback()

}