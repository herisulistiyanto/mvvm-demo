package com.andro.indieschool.mymvvmdemo.domain.usecases

import com.andro.indieschool.mymvvmdemo.base.domain.BaseUseCase
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeMapper
import com.andro.indieschool.mymvvmdemo.data.repo.MemeRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchMemeUseCaseImpl @Inject constructor(
        private val repo: MemeRepo,
        private val mapper: MemeMapper
) : BaseUseCase<FetchMemeUseCase.FetchResult>(), FetchMemeUseCase {

    override fun execute() {
        repo.fetchMemes()
                .map(mapper::mapMeme)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSuccess, ::onError)
                .track()
    }

    private fun onSuccess(memes: List<MemeDto>) {
        liveData.value = FetchMemeUseCase.FetchResult.OnSuccess(memes)
    }

    private fun onError(error: Throwable) {
        liveData.value = FetchMemeUseCase.FetchResult.OnError
    }

}