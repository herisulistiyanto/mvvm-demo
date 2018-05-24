package com.andro.indieschool.mymvvmdemo.di.components

import com.andro.indieschool.mymvvmdemo.MyApp
import com.andro.indieschool.mymvvmdemo.base.di.BaseModule
import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerApplication
import com.andro.indieschool.mymvvmdemo.base.network.di.NetworkModule
import com.andro.indieschool.mymvvmdemo.di.modules.Bindings
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    Bindings::class,
    BaseModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>()

}