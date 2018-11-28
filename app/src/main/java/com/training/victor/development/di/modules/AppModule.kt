package com.training.victor.development.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = context

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}