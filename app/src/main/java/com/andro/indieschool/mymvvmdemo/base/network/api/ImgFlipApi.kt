package com.andro.indieschool.mymvvmdemo.base.network.api

import com.andro.indieschool.mymvvmdemo.base.network.response.MemeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ImgFlipApi {

    @GET("get_memes")
    fun fetchMemes(): Single<MemeResponse>

}