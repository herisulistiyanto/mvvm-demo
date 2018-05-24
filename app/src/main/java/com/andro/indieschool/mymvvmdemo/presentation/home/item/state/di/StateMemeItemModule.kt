package com.andro.indieschool.mymvvmdemo.presentation.home.item.state.di

import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerActivity
import com.andro.indieschool.mymvvmdemo.presentation.home.item.state.StateMemeItem
import com.andro.indieschool.mymvvmdemo.presentation.home.item.state.StateMemeItemImpl
import dagger.Module
import dagger.Provides

@Module
class StateMemeItemModule {

    @Provides
    @PerActivity
    fun providesStateMemeItem(stateMemeItem: StateMemeItemImpl): StateMemeItem = stateMemeItem

}