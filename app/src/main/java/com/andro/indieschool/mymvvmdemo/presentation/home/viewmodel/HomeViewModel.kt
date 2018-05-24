package com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto

abstract class HomeViewModel : ViewModel() {

    sealed class State {
        data class MemeLoaded(val memes: List<MemeDto>) : State()
        object ShowLoading : State()
        object ShowContent: State()
        object ShowError: State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchMemes()

}