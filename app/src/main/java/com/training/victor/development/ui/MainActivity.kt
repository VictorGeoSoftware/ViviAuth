package com.training.victor.development.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.network.responses.LoginResponse
import com.training.victor.development.presenter.LoginPresenter
import com.training.victor.development.utils.showRequestErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LoginPresenter.LoginView {
    @Inject lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        loginPresenter.view = this

        btnLogin.setOnClickListener {
            if (txtUserName.text?.isEmpty()!! || txtPassword.text?.isEmpty()!!) {
                onLoginError(getString(R.string.user_or_password_empty))
            } else {
                loginPresenter.login(txtUserName.text?.toString()!!, txtPassword.text?.toString()!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onLoginError(message: String) {
        showRequestErrorMessage(message)
    }

    // todo :: refactorize this interface, loginResponse not needed
    override fun onLoginSuccessful(loginResponse: LoginResponse) {
        val intent = Intent(this, MedicsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
