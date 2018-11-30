package com.training.victor.development.test

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.training.victor.development.di.modules.NetworkAuthModule.Companion.NORMAL_HTTP_CLIENT
import com.training.victor.development.di.modules.NetworkModule.Companion.AUTH_HTTP_CLIENT
import com.training.victor.development.ui.MainActivity
import com.training.victor.development.ui.MedicsActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import okhttp3.OkHttpClient
import org.hamcrest.Matchers.greaterThan
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
class FirstLaunchTest: ParentInstrumentedTest() {
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Rule
    val medicsActivityTestRule: ActivityTestRule<MedicsActivity> = ActivityTestRule(MedicsActivity::class.java)

    @Inject
    @field:Named(AUTH_HTTP_CLIENT)
    lateinit var authOkHttpClient: OkHttpClient

    @Inject
    @field:Named(NORMAL_HTTP_CLIENT)
    lateinit var normalOkHttpClient: OkHttpClient

    private lateinit var mainActivity: MainActivity
    private lateinit var authIdlingResource: IdlingResource
    private lateinit var normalIdlingResource: IdlingResource


    @Before
    override fun setUp() {
        super.setUp()
        testInstrumentedComponent.inject(this)

        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity

        authIdlingResource = OkHttp3IdlingResource.create("authIdlingResource", normalOkHttpClient)
        normalIdlingResource = OkHttp3IdlingResource.create("normalIdlingResource", authOkHttpClient)
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
        IdlingRegistry.getInstance().register(authIdlingResource)
        onView(withId(R.id.btnLogin)).perform(click())
        Thread.sleep(1000) // loading second activity time
        intended(hasComponent(MedicsActivity::class.java.name))
        IdlingRegistry.getInstance().unregister(authIdlingResource)
    }

    @And("medics list is requested")
    fun medics_list_is_requested() {
        onView(withId(R.id.edtSearchValue)).check(matches(isDisplayed()))
    }

    @Then("list is fulfilled")
    fun list_is_fulfilled() {
        IdlingRegistry.getInstance().register(normalIdlingResource)
        onView(withId(R.id.edtSearchValue)).perform(clearText(), typeText("medico"))
        IdlingRegistry.getInstance().unregister(normalIdlingResource)
        onView(withId(R.id.lstDoctors)).check(withItemCount(greaterThan(0)))
    }
}