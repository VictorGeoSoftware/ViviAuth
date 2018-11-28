package com.training.victor.development.di.modules

import android.support.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.training.victor.development.BuildConfig
import com.training.victor.development.data.Constants.Companion.IDLING_NORMAL
import com.training.victor.development.data.Constants.Companion.IDLING_NORMAL_REQUEST
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class NetworkAuthModule {
    companion object {
        const val NAME_BASE_AUTH_URL = "NAME_BASE_AUTH_URL"
        const val AUTH_REQUEST = "AUTH_REQUEST"
    }


    @Provides
    @Named(NAME_BASE_AUTH_URL)
    fun provideBaseUrlString():String = BuildConfig.API_AUTH_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ==")
                .build()

            return@addInterceptor it.proceed(request)
        }

        return okHttpClient.build()
    }

    @Provides
    @Named(AUTH_REQUEST)
    fun provideRetrofit(okHttpClient: OkHttpClient, converter: Converter.Factory, callAdapterFactory: RxJava2CallAdapterFactory,
                        @Named(NAME_BASE_AUTH_URL) baseUrl:String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addCallAdapterFactory(callAdapterFactory).addConverterFactory(converter).build()
    }

    @Provides
    @Named(IDLING_NORMAL_REQUEST)
    fun provideIdlingResource(okHttpClient: OkHttpClient): IdlingResource {
        return OkHttp3IdlingResource.create(IDLING_NORMAL, okHttpClient)
    }
}