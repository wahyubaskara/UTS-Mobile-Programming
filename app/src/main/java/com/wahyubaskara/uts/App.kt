package com.wahyubaskara.uts

import android.app.Application
import androidx.room.Room
import com.wahyubaskara.uts.database.MainDatabase

open class App: Application() {
    companion object {
        lateinit var database: MainDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, MainDatabase::class.java, "db_mahasiswa").allowMainThreadQueries().build()
    }
}