package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CreditHandelingTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val currentCredit = 500
    private val instrumentPrice = 15
    @Test
    fun creditHandelingTest() {
        onView(allOf(withId(R.id.getStarted))).perform(click())


        onView(allOf(withId(R.id.creditScore), withText("Credit: $currentCredit")))
            .check(matches(withText("Credit: 500")))

        onView(allOf(withText("Black"))).perform(click())




      onView(allOf(withId(R.id.borrowButton), withText("Borrow"))).perform(click())

       onView(allOf(withId(R.id.borrowDuration))).perform(replaceText("1"), closeSoftKeyboard())

       onView(allOf(withId(R.id.confirmButton), withText("Confirm"))).perform(click())


       onView(allOf(withId(R.id.creditScore))).check(matches(withText("Credit: ${currentCredit - instrumentPrice}")))
    }


}
