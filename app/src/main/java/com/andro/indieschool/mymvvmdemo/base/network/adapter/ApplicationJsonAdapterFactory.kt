package com.andro.indieschool.mymvvmdemo.base.network.adapter

import com.squareup.moshi.JsonAdapter
import se.ansman.kotshi.KotshiJsonAdapterFactory

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory: JsonAdapter.Factory {

    companion object {
        val INSTANCE: ApplicationJsonAdapterFactory = KotshiApplicationJsonAdapterFactory()
    }
}