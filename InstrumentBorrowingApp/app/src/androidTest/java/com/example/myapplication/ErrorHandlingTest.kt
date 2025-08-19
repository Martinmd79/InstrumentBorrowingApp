package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ErrorHandlingTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun errorHandlingForEmptyDuration() {
        onView(withId(R.id.getStarted)).perform(click())

        onView(withText("Black")).perform(click())

        onView(withId(R.id.borrowButton)).perform(click())

        onView(withId(R.id.confirmButton)).perform(click())

        onView(withText(containsString("Please enter a valid positive number")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun errorHandlingForInvalidDuration() {
        onView(withId(R.id.getStarted)).perform(click())

        onView(withText("Black")).perform(click())

        onView(withId(R.id.borrowButton)).perform(click())

        onView(withId(R.id.borrowDuration)).perform(replaceText("-1"), closeSoftKeyboard())

        onView(withId(R.id.confirmButton)).perform(click())
        onView(withText(containsString("Please enter a valid positive number")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun errorHandlingForNoColorSelected() {
        onView(withId(R.id.getStarted)).perform(click())

        onView(withId(R.id.borrowButton)).perform(click())
        onView(withText(containsString("Please select a color before proceeding")))
            .check(matches(isDisplayed()))
    }
}
