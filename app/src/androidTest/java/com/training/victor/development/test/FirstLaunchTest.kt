package com.training.victor.development.test

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.development.NormalIdlingResources
import com.training.victor.development.OauthIdlingResources
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.training.victor.development.ui.MainActivity
import com.training.victor.development.ui.MedicsActivity
import com.training.victor.development.utils.myTrace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstLaunchTest: ParentInstrumentedTest() {
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Rule
    val medicsActivityTestRule: ActivityTestRule<MedicsActivity> = ActivityTestRule(MedicsActivity::class.java)

    private lateinit var mainActivity: MainActivity


    @Before
    override fun setUp() {
        super.setUp()
        testInstrumentedComponent.inject(this)

        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity

        myTrace("idling resources :: ${NormalIdlingResources.getmIdlingResource()} ${OauthIdlingResources.getmIdlingResource()}")
        IdlingRegistry.getInstance().register(NormalIdlingResources.getmIdlingResource())
        IdlingRegistry.getInstance().register(OauthIdlingResources.getmIdlingResource())
    }

    @After
    fun tearDown() {
        Intents.release()
        mainActivity.finishAffinity()
    }


    // --------------------------------------------- TEST CASES ---------------------------------------------
    @Given("^a user launch the app for first time")
    fun a_user_launch_the_app_for_first_time() {
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @When("home screen is shown")
    fun home_screen_is_shown() {
        onView(withId(R.id.btnLogin)).perform(click())
        intended(hasComponent(MedicsActivity::class.java.name))
    }

    // todo :: include positioning permission dialog testing case!!
    @And("medics list is requested")
    fun medics_list_is_requested() {
        onView(withId(R.id.edtSearchValue)).check(matches(isDisplayed()))
    }

    // todo :: include error message toast testing case!!
    @Then("list is fulfilled")
    fun list_is_fulfilled() {
        onView(withId(R.id.edtSearchValue)).perform(clearText(), typeText("medico"))
        Thread.sleep(2000) // rx debounce time
        onView(withId(R.id.lstDoctors)).check(withItemCount(greaterThanOrEqualTo(0)))
    }
}