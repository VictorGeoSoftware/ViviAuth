package com.training.victor.development.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class OkHttpClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClientForIdlingResources(): OkHttpClient
            = OkHttpClient.Builder().hostnameVerifier { _, _ -> true }.build()
}