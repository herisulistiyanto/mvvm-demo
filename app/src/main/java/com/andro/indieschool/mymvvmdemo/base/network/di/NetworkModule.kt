package com.andro.indieschool.mymvvmdemo.base.network.di

import com.andro.indieschool.mymvvmdemo.BuildConfig
import com.andro.indieschool.mymvvmdemo.base.di.scopes.PerApplication
import com.andro.indieschool.mymvvmdemo.base.network.adapter.ApplicationJsonAdapterFactory
import com.andro.indieschool.mymvvmdemo.base.network.api.ImgFlipApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {

        const val KEEP_ALIVE_DURATION = (30 * 1000).toLong()
        const val MAX_IDLE_CONNECTIONS = 10
    }

    @Provides
    @PerApplication
    fun providesOkHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

    @Provides
    @PerApplication
    fun providesConnectionPool(): ConnectionPool =
            ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MILLISECONDS)

    @Provides
    @PerApplication
    fun providesMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Provides
    @PerApplication
    fun providesConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @PerApplication
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                             connectionPool: ConnectionPool): OkHttpClient {
        val timeout = 20
        return OkHttpClient.Builder()
                .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
                .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
                .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .connectionPool(connectionPool)
                .build()
    }

    @Provides
    @PerApplication
    fun providesRetrofit(okHttpClient: OkHttpClient,
                         converterFactory: Converter.Factory): Retrofit = Retrofit.Builder()
                                 .addConverterFactory(converterFactory)
                                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                 .client(okHttpClient)
                                 .baseUrl("https://api.imgflip.com/")
                                 .build()

    @Provides
    @PerApplication
    fun providesImgFlipApi(retrofit: Retrofit): ImgFlipApi = retrofit.create(ImgFlipApi::class.java)
}