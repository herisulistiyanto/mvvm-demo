package com.andro.indieschool.mymvvmdemo.data.db.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "memes")
data class MemeEntity(
        @Json(name = "id") @PrimaryKey val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "url") val imageUrl: String,
        @Json(name = "height") val imageHeight: Int,
        @Json(name = "width") val imageWidth: Int
)