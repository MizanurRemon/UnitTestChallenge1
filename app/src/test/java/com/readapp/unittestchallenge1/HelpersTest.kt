package com.readapp.unittestchallenge1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HelpersTest {


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun getUserName() {
        val helpers = Helpers(mainCoroutineRule.testDispatcher)

        runTest {
            helpers.getUserName()
        }

    }


    @Test
    fun addressTest() {
        runTest {
            Helpers(mainCoroutineRule.testDispatcher).getAddress()
        }
    }

    @Test
    fun getAddressDetailTest() {

        var t = Helpers(mainCoroutineRule.testDispatcher)
        runTest {
            t.getAddressDetail()
            mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(true, t.value)
        }
    }
}