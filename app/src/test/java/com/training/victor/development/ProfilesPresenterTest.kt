package com.training.victor.development

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ProfilesPresenterTest: ParentUnitTest() {

//    @Inject lateinit var dataManager: DataManager
//    @Inject lateinit var profilesRepository: ProfilesRepository
//    @Mock lateinit var profilesView: ProfilesPresenter.ProfilesView

//    private lateinit var testScheduler: TestScheduler
//    private lateinit var profilesPresenter: ProfilesPresenter

//    @Before
//    override fun setUp() {
//        super.setUp()
//
//        testComponent.inject(this)
//        MockitoAnnotations.initMocks(this)
//        testScheduler = TestScheduler()
//        profilesPresenter = createMockedPresenter()
//    }

//    private fun createMockedPresenter(): ProfilesPresenter {
//        val profilesPresenter = ProfilesPresenter(testScheduler, testScheduler, dataManager)
//        profilesPresenter.view = profilesView
//        return profilesPresenter
//    }


    // --------------------------------------------- TESTING CASES ---------------------------------------------
//    @Test
//    fun `should call to profiles list and retrieve a list`() {
//        whenever(profilesRepository.getProfilesList()).thenReturn(Observable.just(arrayListOf(ProfileItem(0, "hhttps"))))
//        profilesPresenter.getProfilesList()
//        testScheduler.triggerActions()
//
//        verify(profilesView, times(1)).onProfilesListReceived(any())
//    }

//    @Test
//    fun `should call to profiles list and retrieve an error`() {
//        whenever(profilesRepository.getProfilesList()).thenReturn(Observable.error(Throwable()))
//        profilesPresenter.getProfilesList()
//        testScheduler.triggerActions()
//
//        verify(profilesView, times(1)).onProfilesListError()
//    }
}
