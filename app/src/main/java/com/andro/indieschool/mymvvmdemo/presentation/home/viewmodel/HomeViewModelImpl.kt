package com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.andro.indieschool.mymvvmdemo.domain.usecases.FetchMemeUseCase

class HomeViewModelImpl(
        private val state: MediatorLiveData<HomeViewModel.State>,
        private val useCase: FetchMemeUseCase
) : HomeViewModel() {

    init {
        state.addSource(useCase.getLiveData(), ::onFetchMemeResult)
    }

    override fun getState(): LiveData<State> = state

    override fun fetchMemes() {
        state.value = State.ShowLoading
        useCase.execute()
    }

    private fun onFetchMemeResult(result: FetchMemeUseCase.FetchResult?) {
        when (result) {
            is FetchMemeUseCase.FetchResult.OnSuccess -> {
                state.value = State.MemeLoaded(result.memes)
                state.value = State.ShowContent
            }
            is FetchMemeUseCase.FetchResult.OnError -> state.value = State.ShowError
        }
    }

    override fun onCleared() {
        useCase.cleanUp()
    }
}