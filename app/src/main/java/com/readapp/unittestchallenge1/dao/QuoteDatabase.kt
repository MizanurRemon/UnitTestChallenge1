package com.readapp.unittestchallenge1.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.readapp.unittestchallenge1.Quote


@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuotesDao
}