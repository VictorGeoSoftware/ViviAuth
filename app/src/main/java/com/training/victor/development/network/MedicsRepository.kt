package com.training.victor.development.network

import com.google.gson.JsonObject
import com.training.victor.development.network.responses.GetMedicResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MedicsRepository {

    @GET("/api/users/me/doctors")
    fun getMedicList(@Query("search") search: String,
                     @Query("lat") lat: Double,
                     @Query("lng") lng: Double): Observable<GetMedicResp>

    @GET("/api/doctors/{doctorId}/keys/profilepictures")
    fun getMedicPhoto(@Path("doctorId") doctorId: String): Observable<JsonObject>
}