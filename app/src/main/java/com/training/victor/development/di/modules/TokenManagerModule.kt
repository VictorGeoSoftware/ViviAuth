package com.training.victor.development.di.modules

import com.training.victor.development.data.TokenManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class TokenManagerModule {

    @Provides
    @Singleton
    open fun provideTokenManager(): TokenManager {
        return TokenManager()
    }
}