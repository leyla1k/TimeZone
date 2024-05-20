package com.example.timezone

import android.app.Application
import android.util.Log
import com.example.timezone.data.ResultDB
import com.example.timezone.data.ResultRepository

class App: Application() {
    private val resultDatabase by lazy{ ResultDB.getDatabase(applicationContext)}
    val resultRepository by lazy{
        ResultRepository(resultDatabase.getDao())
    }


}