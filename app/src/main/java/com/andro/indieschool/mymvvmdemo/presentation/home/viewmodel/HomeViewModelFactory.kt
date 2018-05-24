package com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.andro.indieschool.mymvvmdemo.domain.usecases.FetchMemeUseCase
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val useCase: FetchMemeUseCase)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModelImpl(MediatorLiveData(), useCase) as T
    }
}