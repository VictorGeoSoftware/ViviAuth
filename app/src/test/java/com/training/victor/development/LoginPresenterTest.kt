package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.training.victor.development.data.DataManager
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.presenter.LoginPresenter
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest: ParentUnitTest() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var loginRepository: LoginRepository
    @Mock lateinit var loginView: LoginPresenter.LoginView

    private lateinit var testScheduler: TestScheduler
    private lateinit var loginPresenter: LoginPresenter


    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        loginPresenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): LoginPresenter {
        val loginPresenter = LoginPresenter(testScheduler, testScheduler, dataManager)
        loginPresenter.view = loginView
        return loginPresenter
    }


    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to login and get an "Bad credential error"`() {
        val user = "androidChallenge@vivy.com"
        val password = "777"
        loginPresenter.login(user, password)
        testScheduler.triggerActions()

        val message = "Bad credentials"
        verify(loginView, times(1)).onLoginError(message)
    }

    @Test
    fun `should call to login and retrieve Bearer token`() {
        val user = "androidChallenge@vivy.com"
        val password = "88888888"
        loginPresenter.login(user, password)
        testScheduler.triggerActions()

        verify(loginView, times(1)).onLoginSuccessful(any())
    }
}