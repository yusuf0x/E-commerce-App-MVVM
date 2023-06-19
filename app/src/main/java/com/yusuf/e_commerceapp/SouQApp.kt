package com.yusuf.e_commerceapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.yusuf.e_commerceapp.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SouQApp :Application(){
//    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}