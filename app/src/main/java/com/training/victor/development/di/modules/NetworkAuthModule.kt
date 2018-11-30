package com.training.victor.development.di.modules

import com.training.victor.development.BuildConfig
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
        const val NORMAL_HTTP_CLIENT = "NORMAL_HTTP_CLIENT"
        const val AUTH_REQUEST = "AUTH_REQUEST"
    }


    @Provides
    @Named(NAME_BASE_AUTH_URL)
    fun provideBaseUrlString():String = BuildConfig.API_AUTH_URL

    @Provides
    @Singleton
    @Named(NORMAL_HTTP_CLIENT)
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.hostnameVerifier { hostname , session -> true } // for Swagger endpoints
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
    fun provideRetrofit(@Named(NORMAL_HTTP_CLIENT) okHttpClient: OkHttpClient,
                        okHttpClientForIdlingResource: OkHttpClient,
                        converter: Converter.Factory,
                        callAdapterFactory: RxJava2CallAdapterFactory,
                        @Named(NAME_BASE_AUTH_URL) baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .client(okHttpClientForIdlingResource)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converter)
            .build()
    }
}