package com.readapp.unittestchallenge1

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.readapp.unittestchallenge1.dao.QuoteDatabase
import com.readapp.unittestchallenge1.dao.QuotesDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuotesDaoTest {
    lateinit var quotesDatabase: QuoteDatabase
    lateinit var quotesDao: QuotesDao

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        quotesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()

        quotesDao = quotesDatabase.quoteDao()
    }

    @Test
    fun insertIntoRoomDatabaseTest() = runBlocking {
        val quote = Quote(1, "Plant Trees", "Remon")
        quotesDao.insertQuotes(quote)
        val result = quotesDao.getQuotes().getOrAwaitValue()
        assertEquals(1, result.size)
        assertEquals("Plant Trees", result[0].quote)

    }

    @Test
    fun getDataTest(){


    }

    @After
    fun tearDown() {
        quotesDatabase.close()
    }
}