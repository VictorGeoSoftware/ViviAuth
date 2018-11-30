package com.training.victor.development

import com.training.victor.development.di.TestAppModule
import com.training.victor.development.di.TestRetrofitModule
import com.training.victor.development.di.TestTokenManagerModule
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.di.modules.*
import dagger.Component
import javax.inject.Singleton

open class ParentUnitTest {
    open lateinit var testComponent: TestComponent

    @Singleton
    @Component(modules = [AppModule::class, NetworkAuthModule::class, NetworkModule::class,
        RetrofitModule::class, DataManagerModule::class, TokenManagerModule::class])
    interface TestComponent : AppComponent {
        fun inject(target: LoginPresenterTest)
        fun inject(target: MedicsPresenterTest)
    }


    open fun setUp() {
        testComponent = DaggerParentUnitTest_TestComponent.builder()
            .appModule(TestAppModule())
            .tokenManagerModule(TestTokenManagerModule())
            .retrofitModule(TestRetrofitModule())
            .build()
    }
}