package com.training.victor.development.di.modules

import com.training.victor.development.BuildConfig
import com.training.victor.development.NormalIdlingResources
import com.training.victor.development.data.TokenManager
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
open class NetworkModule {
    companion object {
        const val NAME_BASE_URL = "NAME_BASE_URL"
        const val AUTH_HTTP_CLIENT = "AUTH_HTTP_CLIENT"
        const val NORMAL_REQUEST = "NORMAL_REQUEST"
    }


    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString():String = BuildConfig.API_URL

    @Provides
    @Singleton
    @Named(AUTH_HTTP_CLIENT)
    fun provideOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.hostnameVerifier { _, _ -> true } // for Swagger endpoints
        okHttpClient.addInterceptor {
            val tokenValue = tokenManager.currentLoginResponse?.tokenType + " " + tokenManager.currentLoginResponse?.accessToken!!
            val request = it.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", tokenValue)
                .build()

            return@addInterceptor it.proceed(request)
        }

        return okHttpClient.build()
    }

    @Provides
    @Named(NORMAL_REQUEST)
    fun provideRetrofit(@Named(AUTH_HTTP_CLIENT) okHttpClient: OkHttpClient,
                        converter: Converter.Factory,
                        callAdapterFactory: RxJava2CallAdapterFactory,
                        @Named(NAME_BASE_URL) baseUrl:String): Retrofit {

        NormalIdlingResources.registerOkHttp(okHttpClient)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converter)
            .build()
    }
}