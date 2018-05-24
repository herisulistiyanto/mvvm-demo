package com.andro.indieschool.mymvvmdemo.data.repo

import com.andro.indieschool.mymvvmdemo.base.network.api.ImgFlipApi
import com.andro.indieschool.mymvvmdemo.base.network.response.MemeResponse
import io.reactivex.Single
import javax.inject.Inject

class MemeRepoImpl @Inject constructor(private val api: ImgFlipApi): MemeRepo {

    override fun fetchMemes(): Single<MemeResponse> = api.fetchMemes()
}