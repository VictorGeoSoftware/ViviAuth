package com.training.victor.development.di

import android.support.test.espresso.IdlingResource
import com.training.victor.development.di.modules.NetworkAuthModule
import okhttp3.OkHttpClient

class IsntrTestNetworkAuthModule: NetworkAuthModule() {
    override fun provideIdlingResource(okHttpClient: OkHttpClient): IdlingResource {
        return super.provideIdlingResource(okHttpClient)
    }
}