package com.readapp.unittestchallenge1

import kotlinx.coroutines.*

class Helpers(var dispatcher: CoroutineDispatcher) {
    suspend fun getUserName(): String {
        delay(1000)
        return "remon"
    }

    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }

        return "User - remon"
    }

    suspend fun getAddress(): String {
        withContext(dispatcher) {
            delay(5000)
        }

        return "address"
    }


    var value = false
    suspend fun getAddressDetail() {
        CoroutineScope(dispatcher).launch {
            value = true
        }
    }
}