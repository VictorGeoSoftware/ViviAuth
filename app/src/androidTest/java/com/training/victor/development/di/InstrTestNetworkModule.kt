package com.training.victor.development.di

import android.support.test.espresso.IdlingResource
import com.training.victor.development.di.modules.NetworkModule
import okhttp3.OkHttpClient

class InstrTestNetworkModule: NetworkModule() {
    override fun provideIdlingResource(okHttpClient: OkHttpClient): IdlingResource {
        return super.provideIdlingResource(okHttpClient)
    }
}