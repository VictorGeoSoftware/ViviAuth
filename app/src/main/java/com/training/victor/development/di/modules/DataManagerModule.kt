package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.network.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(loginRepository: LoginRepository): DataManager = DataManager(loginRepository)
}