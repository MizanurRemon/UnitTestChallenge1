package com.readapp.unittestchallenge1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.readapp.unittestchallenge1.Quote


@Dao
interface QuotesDao {

    @Insert
    suspend fun insertQuotes(quote: Quote)

    @Query("SELECT * from Quote")
    suspend fun getQuotes(): LiveData<List<Quote>>
}