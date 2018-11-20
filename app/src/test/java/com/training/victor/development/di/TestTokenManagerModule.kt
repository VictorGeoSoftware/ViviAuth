package com.training.victor.development.di

import com.training.victor.development.data.TokenManager
import com.training.victor.development.di.modules.TokenManagerModule

class TestTokenManagerModule: TokenManagerModule() {
    override fun provideTokenManager(): TokenManager {
        return super.provideTokenManager()
//        return Mockito.mock(TokenManager::class.java)
    }
}