package com.training.victor.development

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.di.modules.*
import com.training.victor.development.test.FirstLaunchTest
import dagger.Component
import org.junit.runner.RunWith
import javax.inject.Singleton

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
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
            .appModule(AppModule(InstrumentationRegistry.getTargetContext()))
            .networkModule(NetworkModule())
            .networkAuthModule(NetworkAuthModule())
            .build()
    }

}
