package com.training.victor.development.test

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.training.victor.development.R
import com.training.victor.development.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.training.victor.development.ui.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert
import org.junit.Rule

class FirstLaunchTest {
    @Rule val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
        mainActivity.finishAffinity()
    }


    // --------------------------------------------- TEST CASES ---------------------------------------------
    @Given("^a user launch the app for first time")
    fun a_user_launch_the_app_for_first_time() {
        Assert.assertNotNull(mainActivity)
    }

    @When("home screen is shown")
    fun home_screen_is_shown() {
        onView(withId(R.id.btnLogin)).perform(click())
    }

    @And("medics list is requested")
    fun medics_list_is_requested() {
        Thread.sleep(2000)
        onView(withId(R.id.edtSearchValue)).check(matches(isDisplayed()))
        onView(withId(R.id.edtSearchValue)).perform(clearText(), typeText("medico"))
    }

    @Then("list is fulfilled")
    fun list_is_fulfilled() {
        Thread.sleep(5000)
        onView(withId(R.id.lstDoctors)).check(withItemCount(greaterThan(0)))
    }
}