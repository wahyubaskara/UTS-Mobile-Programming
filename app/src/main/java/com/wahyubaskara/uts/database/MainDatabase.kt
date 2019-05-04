package com.wahyubaskara.uts.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wahyubaskara.uts.database.dao.MahasiswaDao
import com.wahyubaskara.uts.database.models.MahasiswaData

@Database(entities = [MahasiswaData::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
}