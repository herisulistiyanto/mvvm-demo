package com.andro.indieschool.mymvvmdemo.data.repo

import com.andro.indieschool.mymvvmdemo.base.network.response.MemeResponse
import io.reactivex.Single

interface MemeRepo {

    fun fetchMemes(): Single<MemeResponse>

}