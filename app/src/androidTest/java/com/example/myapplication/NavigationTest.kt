package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.navtest.*
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testButtons() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        checkBnToFirst()
        Espresso.onView(withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        checkBnToThird()
        checkBnToSecond()
        Espresso.onView(withId(R.id.bnToThird))
            .perform(ViewActions.click())
        checkBnToFirst()
    }

    @Test
    fun testAbout() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        aboutFromFirst()
        Espresso.onView(withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        aboutFromSecond()
        Espresso.onView(withId(R.id.bnToThird))
            .perform(ViewActions.click())
        aboutFromThird()
    }

    @Test
    fun testAbout2() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}