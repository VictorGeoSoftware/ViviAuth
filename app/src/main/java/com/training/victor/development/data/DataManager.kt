package com.training.victor.development.data

import com.training.victor.development.network.LoginRepository
import com.training.victor.development.network.responses.LoginResponse
import io.reactivex.Observable

class DataManager(private val loginRepository: LoginRepository) {

    var currentLoginResponse: LoginResponse? = null

    fun login(user: String, password: String): Observable<LoginResponse> {
        return loginRepository.login(user, password).flatMap {
            currentLoginResponse = it
            Observable.just(currentLoginResponse)
        }
    }

}