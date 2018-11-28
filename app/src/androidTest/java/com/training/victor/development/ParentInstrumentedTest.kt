package com.training.victor.development

import android.support.test.runner.AndroidJUnit4
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.test.FirstLaunchTest
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ParentInstrumentedTest {
    /*
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
     */

    open lateinit var testInstrumentedComponent: TestInstrumentedComponent


    interface TestInstrumentedComponent: AppComponent {
        fun inject(target: FirstLaunchTest)
    }


    open fun setUp() {

    }

}
