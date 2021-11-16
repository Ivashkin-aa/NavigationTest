package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecreateTest {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    private fun recreateFragment(from: Int) {
        onView(withId(from))
            .check(matches(isDisplayed()))
        activity.scenario.recreate()
        onView(withId(from))
            .check(matches(isDisplayed()))
    }

    private fun checkButton(from: Int) {
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(matches(isDisplayed()))
        recreateFragment(from)
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(matches(isDisplayed()))
    }

    @Test
    fun recreatingTest() {
        recreateFragment(R.id.fragment1)
        onView(withId(R.id.bnToSecond)).perform(ViewActions.click())
        recreateFragment(R.id.fragment2)
        onView(withId(R.id.bnToThird)).perform(ViewActions.click())
        recreateFragment(R.id.fragment3)
    }

    @Test
    fun checkNavUp() {
        onView(withId(R.id.fragment1)).
                check(matches(isDisplayed()))
        checkBnToSecond()
        checkButton(R.id.fragment2)
        checkBnToThird()
        checkButton(R.id.fragment3)
    }
}
