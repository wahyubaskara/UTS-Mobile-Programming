package com.wahyubaskara.uts.database.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_mahasiswa")
data class MahasiswaData(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,

    val nim: String,

    val nama: String,

    val jurusan: String,

    val telp: String,

    val alamat: String

)