package com.training.victor.development

import com.training.victor.development.di.TestNetworkModule
import com.training.victor.development.di.components.NetworkComponent
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.di.modules.NetworkModule
import dagger.Component
import org.junit.Before
import javax.inject.Singleton

open class ParentUnitTest {
    open lateinit var testNetworkComponent: TestNetworkComponent

    @Singleton
    @Component(modules = [NetworkModule::class, DataManagerModule::class])
    interface TestNetworkComponent : NetworkComponent {
        fun inject(target: LoginPresenterTest)
        fun inject(target: MedicsPresenterTest)
    }

    @Before
    open fun setUp() {
        testNetworkComponent = DaggerParentUnitTest_TestNetworkComponent.builder()
            .networkModule(TestNetworkModule())
            .build()
    }
}