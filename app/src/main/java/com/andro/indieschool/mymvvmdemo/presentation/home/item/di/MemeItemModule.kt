package com.andro.indieschool.mymvvmdemo.presentation.home.item.di

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.andro.indieschool.mymvvmdemo.base.di.qualifiers.ForActivity
import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerActivity
import com.andro.indieschool.mymvvmdemo.presentation.home.item.MemeItemView
import com.andro.indieschool.mymvvmdemo.presentation.home.item.MemeItemViewImpl
import com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter.MemesAdapter
import com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter.MemesAdapterImpl
import dagger.Module
import dagger.Provides

@Module
class MemeItemModule {

    @Provides
    @PerActivity
    fun providesMemeItemView(memeItemView: MemeItemViewImpl): MemeItemView = memeItemView

    @Provides
    @PerActivity
    fun provideListActionLiveData(): MutableLiveData<MemeItemView.Action> = MutableLiveData()

    @Provides
    @PerActivity
    fun providesLinearLayoutManager(@ForActivity context: Context): LinearLayoutManager =
            LinearLayoutManager(context)

    @Provides
    @PerActivity
    fun providesMemesAdapter(adapter: MemesAdapterImpl): MemesAdapter = adapter

}