package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.MedicItem
import com.training.victor.development.utils.getErrorMessage
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import javax.inject.Inject

class MedicsPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager): Presenter<MedicsPresenter.MedicsView>() {


    interface MedicsView {
        fun showProgressBar(show: Boolean)
        fun onMedicListError(message: String)
        fun onMedicListReceived(medicList: List<MedicItem>)
        fun onUserNotFound()
    }

    fun getMedicListAuth(doctorName: String, lat: Double, long: Double) {
        if (dataManager.mUser.isEmpty() && dataManager.mPassword.isEmpty()) {
            view?.onUserNotFound()
        } else {
            view?.showProgressBar(true)
            compositeDisposable.clear()

            compositeDisposable.add(dataManager.login(dataManager.mUser, dataManager.mPassword)
                .flatMap {
                    dataManager.getMedicsList(doctorName, lat, long)
                }
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe({
                    view?.showProgressBar(false)
                    view?.onMedicListReceived(it)

                },{
                    view?.showProgressBar(false)
                    view?.onMedicListError(it.getErrorMessage())
                }))
        }
    }

    // todo :: as soon as I solve the update_time doubt, implement this methods with the corresponding refresh logic
    fun getMedicList(name: String, lat: Double, long: Double) {
        view?.showProgressBar(true)

        compositeDisposable.add(dataManager.getMedicsList(name, lat, long)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)

            },{
                view?.showProgressBar(false)
                view?.onMedicListError(it.getErrorMessage())
            }))
    }

    fun getMedicPhotoAuth(medicId: String) {
        compositeDisposable.add(dataManager.login(dataManager.mUser, dataManager.mPassword)
            .flatMap { dataManager.getMedicPhoto(medicId) }
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                myTrace("getMedicPhoto - success :: $it")
            },{
                myTrace("getMedicPhoto - error :: $it")
            }))
    }

    fun getMedicPhoto(medicId: String) {
        compositeDisposable.add(dataManager.getMedicPhoto(medicId)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                myTrace("getMedicPhoto - success :: $it")
            },{
                myTrace("getMedicPhoto - error :: $it")
            }))
    }
}