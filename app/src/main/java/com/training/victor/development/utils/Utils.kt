package com.training.victor.development.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson
import com.training.victor.development.R
import com.training.victor.development.network.responses.LoginErrorResp
import retrofit2.HttpException

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.getDpFromValue(value: Int): Int =
    Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), this.resources.displayMetrics))

fun Any.myTrace(message: String) {
    System.out.println("victor - ${this.javaClass.name} - $message")
}

fun Throwable.getErrorMessage(): String {
    return if (this is HttpException) {
        val responseBody = this.response().errorBody()
        Gson().fromJson<LoginErrorResp>(responseBody?.string(), LoginErrorResp::class.java).errorDescription
    } else {
        this.localizedMessage
    }
}

fun Activity.showRequestErrorMessage(cause: String) {
    val errorMessage = String.format(this.getString(R.string.error_message), cause)
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}