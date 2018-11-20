package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName

data class LoginErrorResp (@SerializedName("error") val error: String,
                           @SerializedName("error_description") val errorDescription: String)