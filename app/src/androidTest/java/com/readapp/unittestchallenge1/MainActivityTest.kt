package com.readapp.unittestchallenge1


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class MainActivityTest {

    private var LAUNCH_TIMEOUT = 2500;
    private lateinit var device: UiDevice
    private val BASIC_SAMPLE_PACKAGE = "com.internetpolice.app"
    @get: Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() {
        Log.d("dataxx", "setUp: ")
    }

    @After
    fun tearDown() {
        Log.d("dataxx", "tearDown: ")
    }

    @Test
    fun startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        device.waitForWindowUpdate("com.internetpolice.app", 5000)
        device.setOrientationRight();
        device.waitForWindowUpdate(null, 5000)
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, 5000)
        device.setOrientationNatural()

        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE).apply {
            // Clear out any previous instances
            this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )



    }

    @Test
    fun openNotification(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openNotification()
    }

    @Test
    fun openQuickSettings(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openQuickSettings()
    }

    @Test
    fun getSystemClock(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        var clock: UiObject2 = device.findObject(By.res("com.android.systemui:id/clock"))
        Log.d("dataxx", "getSystemClock: ${clock.text}")
    }


//    @Test
//    fun submitButton_test(){
//        onView(withId(R.id.titleEditText)).perform(typeText("hi"))
//        onView(withId(R.id.descriptionEditText)).perform(typeText("sample"), closeSoftKeyboard())
//
//        onView(withId(R.id.submitButton)).perform(click())
//
//        onView(withId(R.id.contentText)).check(matches(withText("hi sample")))
//    }

}