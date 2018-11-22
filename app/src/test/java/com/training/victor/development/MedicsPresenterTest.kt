package com.training.victor.development

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.TokenManager
import com.training.victor.development.data.models.MedicItem
import com.training.victor.development.presenter.MedicsPresenter
import com.training.victor.development.utils.createMockedLoginResponseExpired
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MedicsPresenterTest: ParentUnitTest() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var tokenManager: TokenManager
    @Mock lateinit var medicsView: MedicsPresenter.MedicsView

    private lateinit var testScheduler: TestScheduler
    private lateinit var medicsPresenter: MedicsPresenter


    @Before
    override fun setUp() {
        super.setUp()

        testComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        medicsPresenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): MedicsPresenter {
        val loginPresenter = MedicsPresenter(testScheduler, testScheduler, dataManager)
        loginPresenter.view = medicsView
        return loginPresenter
    }

    

    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to medic list and get medic list`() {
        val user = "androidChallenge@vivy.com"
        val password = "88888888"
        dataManager.mUser = user
        dataManager.mPassword = password
        val medicName = "Doctor"
        val lat = 52.534709
        val long = 13.3976972

        medicsPresenter.getMedicListAuth(medicName, lat, long)
        testScheduler.triggerActions()

        val medicList = ArrayList<MedicItem>()
        verify(medicsView, times(1)).onMedicListReceived(medicList)
    }

    @Test
    fun `should call to medic list with expired token bearer`() {
        val user = "androidChallenge@vivy.com"
        val password = "88888888"
        dataManager.mUser = user
        dataManager.mPassword = password
        tokenManager.currentLoginResponse = createMockedLoginResponseExpired()
        val medicName = "Doctor"
        val lat = 52.534709
        val long = 13.3976972

        medicsPresenter.getMedicList(medicName, lat, long)
        testScheduler.triggerActions()

        verify(medicsView, times(1)).onAccessTokenExpired()
    }

    @Test
    fun `should call to medic list and get some authentication error`() {
        val user = "androidChallenge@vivy"
        val password = "88888888"
        dataManager.mUser = user
        dataManager.mPassword = password
        val medicName = "Victor"
        val lat = 52.534709
        val long = 13.3976972

        medicsPresenter.getMedicListAuth(medicName, lat, long)
        testScheduler.triggerActions()

        val message = "Bad credentials"
        verify(medicsView, times(1)).onMedicListError(message)
    }

    @Test
    fun `should request a photo by doctorId and retrieve something`() {
        val user = "androidChallenge@vivy.com"
        val password = "88888888"
        dataManager.mUser = user
        dataManager.mPassword = password

        val medicId = "ChIJ25zpFiZ0dEgRocoL95RWb2A"


        medicsPresenter.getMedicPhotoAuth(medicId)
        testScheduler.triggerActions()
    }
}