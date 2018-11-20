package com.training.victor.development.di

import android.app.Application
import com.training.victor.development.di.modules.AppModule
import org.mockito.Mockito

class TestAppModule: AppModule(Mockito.mock(Application::class.java))