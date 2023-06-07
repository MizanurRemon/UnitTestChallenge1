package com.readapp.unittestchallenge1

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

  /*  @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }*/

    @get: Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun submitButton_test(){
        onView(withId(R.id.titleEditText)).perform(typeText("hi"))
        onView(withId(R.id.descriptionEditText)).perform(typeText("remon"), closeSoftKeyboard())

        onView(withId(R.id.submitButton)).perform(click())

        onView(withId(R.id.contentText)).check(matches(withText("hi remon")))
    }
}