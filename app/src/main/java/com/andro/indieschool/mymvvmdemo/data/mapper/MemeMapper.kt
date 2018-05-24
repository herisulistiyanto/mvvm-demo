package com.andro.indieschool.mymvvmdemo.data.mapper

import com.andro.indieschool.mymvvmdemo.base.network.response.MemeResponse
import javax.inject.Inject

class MemeMapper @Inject constructor() {

    fun mapMeme(response: MemeResponse): List<MemeDto> {
        return response.data.memes.map {
            MemeDto(
                    it.name,
                    it.imageUrl,
                    it.imageWidth,
                    it.imageHeight
            )
        }
    }

}