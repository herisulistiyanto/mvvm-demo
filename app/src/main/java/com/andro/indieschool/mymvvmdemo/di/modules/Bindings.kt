package com.andro.indieschool.mymvvmdemo.di.modules

import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerActivity
import com.andro.indieschool.mymvvmdemo.presentation.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [MemeListModule::class])
    abstract fun bindHomeActivity(): HomeActivity

}