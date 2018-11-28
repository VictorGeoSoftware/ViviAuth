package com.training.victor.development.test

import com.training.victor.development.di.InstrTestNetworkModule
import com.training.victor.development.di.InstrumentedTestAppModule
import com.training.victor.development.di.IsntrTestNetworkAuthModule
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.di.modules.*
import dagger.Component
import javax.inject.Singleton

open class ParentInstrumentedTest {
    open lateinit var testInstrumentedComponent: TestInstrumentedComponent

    @Singleton
    @Component(modules = [AppModule::class, NetworkAuthModule::class, NetworkModule::class,
        RetrofitModule::class, DataManagerModule::class, TokenManagerModule::class])
    interface TestInstrumentedComponent: AppComponent {
        fun inject(target: FirstLaunchTest)
    }


    open fun setUp() {
        testInstrumentedComponent = DaggerParentInstrumentedTest_TestInstrumentedComponent.builder()
            .appModule(InstrumentedTestAppModule())
            .networkModule(InstrTestNetworkModule())
            .networkAuthModule(IsntrTestNetworkAuthModule())
            .build()
    }

}
