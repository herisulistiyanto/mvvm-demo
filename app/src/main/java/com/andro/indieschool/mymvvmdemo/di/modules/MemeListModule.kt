package com.andro.indieschool.mymvvmdemo.di.modules

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.andro.indieschool.mymvvmdemo.base.di.qualifiers.ForActivity
import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerActivity
import com.andro.indieschool.mymvvmdemo.data.db.MemeDao
import com.andro.indieschool.mymvvmdemo.data.db.MemeDb
import com.andro.indieschool.mymvvmdemo.data.repo.MemeRepo
import com.andro.indieschool.mymvvmdemo.data.repo.MemeRepoImpl
import com.andro.indieschool.mymvvmdemo.domain.usecases.FetchMemeUseCase
import com.andro.indieschool.mymvvmdemo.domain.usecases.FetchMemeUseCaseImpl
import com.andro.indieschool.mymvvmdemo.presentation.home.HomeActivity
import com.andro.indieschool.mymvvmdemo.presentation.home.item.di.MemeItemModule
import com.andro.indieschool.mymvvmdemo.presentation.home.item.state.di.StateMemeItemModule
import com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel.HomeViewModel
import com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [
    MemeItemModule::class,
    StateMemeItemModule::class
])
class MemeListModule {

    @Provides
    @PerActivity
    fun providesMemeRepo(repo: MemeRepoImpl): MemeRepo = repo

    @Provides
    @PerActivity
    fun providesMemeFetchUseCase(useCase: FetchMemeUseCaseImpl): FetchMemeUseCase = useCase

    @Provides
    @PerActivity
    fun providesListViewModel(activity: HomeActivity, factory: HomeViewModelFactory) : HomeViewModel {
        return ViewModelProviders.of(activity, factory).get(HomeViewModel::class.java)
    }

    @Provides
    @PerActivity
    @ForActivity
    fun providesContext(activity: HomeActivity) : Context = activity

    @Provides
    @PerActivity
    fun providesMemeDao(@ForActivity context: Context): MemeDao {
        val db = MemeDb.create(context, false)
        return db.crud()
    }

}