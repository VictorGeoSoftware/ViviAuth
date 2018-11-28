package com.training.victor.development.di

import android.support.test.InstrumentationRegistry
import com.training.victor.development.di.modules.AppModule

class InstrumentedTestAppModule: AppModule(InstrumentationRegistry.getTargetContext())