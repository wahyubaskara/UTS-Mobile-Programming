package com.wahyubaskara.uts.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.wahyubaskara.uts.database.models.MahasiswaData

@Dao
interface MahasiswaDao {

    @Insert(onConflict = REPLACE)
    fun insertData(data: MahasiswaData)

    @Query("SELECT * FROM tb_mahasiswa")
    fun getAllData() : List<MahasiswaData>

    @Update
    fun editData(mahasiswaData: MahasiswaData)

    @Delete
    fun deleteById(mahasiswaData: MahasiswaData)

}