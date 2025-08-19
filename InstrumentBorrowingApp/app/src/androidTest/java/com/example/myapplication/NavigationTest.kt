package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class NavigationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun navigationTest() {
        //check to see if the Get Started button takes the user to the second activity, the instrument browsing.
        onView(allOf(withId(R.id.getStarted))).perform(click())
        onView(allOf(withId(R.id.instrumentDetailsLayout))).check(matches(isDisplayed()))


        onView(allOf(withText("Black"))).perform(click())
        onView(allOf(withId(R.id.borrowButton))).perform(click())

        // check to see if the the correct layout is displayed after clicking on the borrow button
        onView(allOf(withId(R.id.borrowDetailsActivity))).check(matches(isDisplayed()))
    }


}
