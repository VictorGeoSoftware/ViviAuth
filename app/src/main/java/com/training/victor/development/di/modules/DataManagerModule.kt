package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.TokenManager
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.network.MedicsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(loginRepository: LoginRepository,
                           medicsRepository: MedicsRepository,
                           tokenManager: TokenManager): DataManager
            = DataManager(loginRepository, medicsRepository, tokenManager)
}