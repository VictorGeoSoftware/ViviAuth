package com.training.victor.development.data

import com.google.gson.JsonObject
import com.training.victor.development.data.models.MedicItem
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.network.MedicsRepository
import com.training.victor.development.network.responses.GetMedicResp
import com.training.victor.development.network.responses.LoginResponse
import io.reactivex.Observable

class DataManager(private val loginRepository: LoginRepository,
                  private val medicsRepository: MedicsRepository,
                  private val tokenManager: TokenManager) {

    var mUser: String = ""
    var mPassword: String = ""

    fun login(user: String, password: String): Observable<LoginResponse> {
        return loginRepository.login(user, password).flatMap {
            tokenManager.currentLoginResponse = it
            mUser = user
            mPassword = password
            Observable.just(it)
        }
    }

    fun getMedicsList(name: String, lat: Double, long: Double): Observable<List<MedicItem>> {
        return medicsRepository.getMedicList(name, lat, long)
            .flatMap { medicResp ->
                Observable.just(medicResp.doctors.map { MedicItem(it.name, it.address, it.photoId) })
            }
    }

    fun getMedicPhoto(medicId: String): Observable<JsonObject> {
        return medicsRepository.getMedicPhoto(medicId)
    }

}