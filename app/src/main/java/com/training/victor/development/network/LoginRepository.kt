package com.training.victor.development.network

import com.training.victor.development.network.responses.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginRepository {

    @FormUrlEncoded
    @POST("/oauth/token?grant_type=password")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginResponse>
}