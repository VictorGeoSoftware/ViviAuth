package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import io.reactivex.Scheduler
import javax.inject.Inject

class MedicsPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager): Presenter<MedicsPresenter.MedicsView>() {

    interface MedicsView {

    }
}