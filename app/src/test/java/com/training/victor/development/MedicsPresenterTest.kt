package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.training.victor.development.data.DataManager
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.presenter.LoginPresenter
import com.training.victor.development.presenter.MedicsPresenter
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MedicsPresenterTest: ParentUnitTest() {
    @Inject lateinit var dataManager: DataManager
    @Mock lateinit var medicsView: MedicsPresenter.MedicsView

    private lateinit var testScheduler: TestScheduler
    private lateinit var medicsPresenter: MedicsPresenter


    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        medicsPresenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): MedicsPresenter {
        val loginPresenter = MedicsPresenter(testScheduler, testScheduler, dataManager)
        loginPresenter.view = medicsView
        return loginPresenter
    }

    // todo :: implementar UI para splash de login y presentar otra activity para la busqueda de doctores!

    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to medic list and retrieve some medic list`() {
        val bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyb2lkQ2hhbGxlbmdlQHZpdnkuY29tIiwic3R5cGUiOiJVU0VSIiwic2NvcGUiOlsiYmFzaWMiXSwiaWQiOiJlY2I3NDBlNS0wNzMxLTQ3ZWEtODAyNC03YzFjYTlhZWQzMjciLCJleHAiOjE1NDI3MDI1ODksImp0aSI6ImY0M2JiNjhjLTZhNGItNDA5OC04MDdjLTI4YWMyMzlmMTBjMCIsImNsaWVudF9pZCI6ImlwaG9uZSJ9.Fp1rQrkban4P4rLhh12TMOSW5Qbwe3jLjhasEBv7mS8"
        val user = "androidChallenge@vivy.com"
        val password = "777"

        //{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyb2lkQ2hhbGxlbmdlQHZpdnkuY29tIiwic3R5cGUiOiJVU0VSIiwic2NvcGUiOlsiYmFzaWMiXSwiaWQiOiJlY2I3NDBlNS0wNzMxLTQ3ZWEtODAyNC03YzFjYTlhZWQzMjciLCJleHAiOjE1NDI3MDI1ODksImp0aSI6ImY0M2JiNjhjLTZhNGItNDA5OC04MDdjLTI4YWMyMzlmMTBjMCIsImNsaWVudF9pZCI6ImlwaG9uZSJ9.Fp1rQrkban4P4rLhh12TMOSW5Qbwe3jLjhasEBv7mS8","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyb2lkQ2hhbGxlbmdlQHZpdnkuY29tIiwic3R5cGUiOiJVU0VSIiwic2NvcGUiOlsiYmFzaWMiXSwiYXRpIjoiZjQzYmI2OGMtNmE0Yi00MDk4LTgwN2MtMjhhYzIzOWYxMGMwIiwiaWQiOiJlY2I3NDBlNS0wNzMxLTQ3ZWEtODAyNC03YzFjYTlhZWQzMjciLCJleHAiOjE1NDUyOTQyODksImp0aSI6ImNlZjExNWQ4LWJhZmItNGYwNS1iZGM1LTA5NDgyYTYzYTZhMSIsImNsaWVudF9pZCI6ImlwaG9uZSJ9.GlSUgAY_JbzDUE00imLkyfIFpbW8v5rsxfRTjD9T5uE","expires_in":299,"scope":"basic","jti":"f43bb68c-6a4b-4098-807c-28ac239f10c0","phoneVerified":false}

//        loginPresenter.login(user, password)
        testScheduler.triggerActions()

        val message = "Bad credentials"
//        verify(loginView, times(1)).onLoginError(message)
    }

}