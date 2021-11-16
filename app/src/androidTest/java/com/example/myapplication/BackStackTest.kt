package com.example.myapplication

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BackStackTest {
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun firstTest() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        checkBnToFirst()
        Espresso.pressBackUnconditionally()
        Assert.assertTrue(activity.scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun secondTest() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        checkBnToThird()
        checkBnToSecond()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        checkBnToThird()
        checkBnToFirst()
        Espresso.pressBackUnconditionally()
        Assert.assertTrue(activity.scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun thirdTest() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        aboutFromFirst()
        checkBnToSecond()
        aboutFromSecond()
        checkBnToThird()
        aboutFromThird()
    }

    @Test
    fun fourthTest() {
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        openAbout()
        navigateUp(1)
        checkBnToThird()
        checkBnToSecond()
        navigateUp(1)
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        checkBnToSecond()
        checkBnToThird()
        openAbout()
        navigateUp(3)
        Espresso.onView(withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
