package com.andro.indieschool.mymvvmdemo.domain.usecases

import com.andro.indieschool.mymvvmdemo.base.domain.UseCase
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto

interface FetchMemeUseCase  : UseCase<FetchMemeUseCase.FetchResult>{

    sealed class FetchResult {
        data class OnSuccess(val memes: List<MemeDto>) : FetchResult()
        object OnError : FetchResult()
    }

    fun execute()
}