package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.di.scopes.ViewScope
import com.training.victor.development.presenter.LoginPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

@Module
class PresenterModule {

    companion object {
        const val ANDROID_SCHEDULER = "ANDROID_SCHEDULER"
        const val TASK_SCHEDULER = "TASK_SCHEDULER"
    }



    @Provides
    @Named(ANDROID_SCHEDULER)
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(TASK_SCHEDULER)
    fun provideTaskScheduler(): Scheduler = Schedulers.newThread()



    @Provides
    @ViewScope
    fun provideLoginPresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                                 @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                                 dataManager: DataManager)
            = LoginPresenter(androidScheduler, taskScheduler, dataManager)
}