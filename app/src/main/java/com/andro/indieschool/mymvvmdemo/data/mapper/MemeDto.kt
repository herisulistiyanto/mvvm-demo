package com.andro.indieschool.mymvvmdemo.data.mapper

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class MemeDto(
        val name: String = "",
        val imgUrl: String = "",
        val imgWidth: Int = 0,
        val imgHeight: Int = 0
) : Parcelable