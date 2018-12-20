package com.training.victor.development.di.modules

import com.training.victor.development.di.modules.NetworkAuthModule.Companion.AUTH_REQUEST
import com.training.victor.development.di.modules.NetworkModule.Companion.NORMAL_REQUEST
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.network.MedicsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class RetrofitModule {

    @Provides
    @Singleton
    open fun provideLoginRepository(@Named(AUTH_REQUEST) retrofit: Retrofit) = retrofit.create(LoginRepository::class.java)!!

    @Provides
    @Singleton
    open fun provideMedicRepository(@Named(NORMAL_REQUEST) retrofit: Retrofit) = retrofit.create(MedicsRepository::class.java)!!
}