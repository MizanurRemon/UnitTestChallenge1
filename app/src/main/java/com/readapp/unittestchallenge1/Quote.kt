package com.readapp.unittestchallenge1

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quote: String,
    val author: String
)
