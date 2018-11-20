package com.training.victor.development.di

import com.training.victor.development.di.modules.RetrofitModule
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.network.MedicsRepository
import retrofit2.Retrofit

class TestRetrofitModule: RetrofitModule() {

    override fun provideLoginRepository(retrofit: Retrofit): LoginRepository {
        //Mockito.mock(LoginRepository::class.java)
        return super.provideLoginRepository(retrofit)
    }

    override fun provideMedicRepository(retrofit: Retrofit): MedicsRepository {
        return super.provideMedicRepository(retrofit)
    }
}