package com.readapp.unittestchallenge1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Helpers {
    suspend fun getUserName(): String{
        delay(1000)
        return "remon"
    }

    suspend fun getUser() : String{
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }

        return "User - remon"
    }
}