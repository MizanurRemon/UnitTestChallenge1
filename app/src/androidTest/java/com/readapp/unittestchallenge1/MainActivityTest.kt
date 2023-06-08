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
import org.junit.*

import org.junit.runner.RunWith
import java.io.IOException


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

    @Test(expected = Exception::class)
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
        //assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE
        ).apply {
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
    fun openNotification() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openNotification()
    }

    @Test
    fun openQuickSettings() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openQuickSettings()
    }

    @Test()
    fun openSettings() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.performActionAndWait({
            try {
                device.executeShellCommand("am start -a android.settings.SETTINGS")
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }, Until.newWindow(), 1000)
        // Check system settings has been opened.
//        Assert.assertTrue(device.hasObject(By.pkg("com.android.settings")))

//        // Scroll the settings to the top and find Notifications button
        var scrollableObj: UiObject2 = device.findObject(By.scrollable(true))
        scrollableObj.scrollUntil(Direction.UP, Until.scrollFinished(Direction.UP))
        val notificationsButton = scrollableObj.findObject(By.text("Notifications"))
//
//        // Click the Notifications button and wait until a new window is opened.
        device.performActionAndWait({ notificationsButton.click() }, Until.newWindow(), 1000)
        scrollableObj = device.findObject(By.scrollable(true))
//        // Scroll down until it finds a Do Not Disturb button.
        val doNotDisturb = scrollableObj.scrollUntil(
            Direction.DOWN,
            Until.findObject(By.textContains("Do Not Disturb"))
        )
        device.performActionAndWait({ doNotDisturb.click() }, Until.newWindow(), 1000)
//        // Turn off the Do Not Disturb.
        val turnOnDoNotDisturb = device.findObject(By.text("Turn off now"))
        turnOnDoNotDisturb?.click()
//        Assert.assertTrue(device.wait(Until.hasObject(By.text("Turn off now")), 1000))
    }

    @Test()
    fun getSystemClock() {
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

    @Test
    fun bluetoothControlTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.performActionAndWait({
            try {
                device.executeShellCommand("am start -a android.settings.SETTINGS")
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }, Until.newWindow(), 1000)


        val scrollObject: UiObject2 = device.findObject(By.scrollable(true))
        scrollObject.scrollUntil(Direction.UP, Until.scrollFinished(Direction.UP))

        val connectedDevices = scrollObject.findObject(By.text("Connected devices"))

        device.performActionAndWait({ connectedDevices.click() }, Until.newWindow(), 1000)

        val connectionPreferences: UiObject2 = device.findObject(By.text("Connection preferences"))
        device.performActionAndWait({ connectionPreferences.click() }, Until.newWindow(), 1000)



        //val bluetooth: UiObject2 = device.findObject(By.text("Bluetooth"))
        device.performActionAndWait(
            { device.findObject(By.text("Bluetooth")).click() },
            Until.newWindow(),
            1000
        )


        device.findObject(By.text("Use Bluetooth")).click()
    }

}