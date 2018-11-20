package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.network.responses.LoginResponse
import com.training.victor.development.utils.getErrorMessage
import com.training.victor.development.utils.myTrace
import io.reactivex.Scheduler
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                         private val subscriberSchedulers: Scheduler,
                                         private val dataManager: DataManager): Presenter<LoginPresenter.LoginView>() {

    interface LoginView {
        fun showProgressBar(show: Boolean)
        fun onLoginError(message: String)
        fun onLoginSuccessful(loginResponse: LoginResponse)
    }



    fun login(user: String, password: String) {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.login(user, password)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.showProgressBar(false)
                view?.onLoginSuccessful(it)
            },{
                myTrace("LoginPresenter login error :: ${it.localizedMessage}")
                view?.showProgressBar(false)
                view?.onLoginError(it.getErrorMessage())
            }))
    }
}