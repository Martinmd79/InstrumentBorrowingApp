package com.example.myapplication


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class InstrumentBrowsingTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    private fun verifyInstrumentDetails(name: String, imageDesc: String, description: String, price: String) {
        onView(withId(R.id.instrumentName)).check(matches(withText(name)))
        onView(allOf(withId(R.id.instrumentImage), withContentDescription(imageDesc))).check(matches(isDisplayed()))
        onView(withId(R.id.instrumentDescription)).check(matches(withText(description)))
        onView(withId(R.id.instrumentPrice)).check(matches(withText(price)))
    }

    @Test
    fun verifyInstrumentDetailsChangeOnNextClick() {

        onView(withId(R.id.getStarted)).perform(click())

        //  details for the first instrument (Guitar)
        verifyInstrumentDetails(
            name = "Guitar",
            imageDesc = "Guitar",
            description = "A classic acoustic guitar.",
            price = "15 credits per month"
        )

        onView(withId(R.id.nextButton)).perform(click())

        //  details for the second instrument (Piano)
        verifyInstrumentDetails(
            name = "Piano",
            imageDesc = "Piano",
            description = "An upright piano for all genres.",
            price = "25 credits per month"
        )

        onView(withId(R.id.nextButton)).perform(click())

        //  details for the third instrument (Drum)
        verifyInstrumentDetails(
            name = "Drum Set",
            imageDesc = "Drum Set",
            description = "A full drum set for rock and jazz.",
            price = "20 credits per month"
        )
    }
}
