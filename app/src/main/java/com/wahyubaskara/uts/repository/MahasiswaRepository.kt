package com.wahyubaskara.uts.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahyubaskara.uts.App
import com.wahyubaskara.uts.database.models.MahasiswaData


class MahasiswaRepository {
    private val listMahasiswa = MutableLiveData<List<MahasiswaData>>()

    companion object {
        private var mahasiswaRepository: MahasiswaRepository? = null
        @Synchronized
        @JvmStatic
        fun getInstance(): MahasiswaRepository {
            if (mahasiswaRepository == null) { mahasiswaRepository = MahasiswaRepository() }
            return mahasiswaRepository!!
        }
    }

    fun subscribe(): LiveData<List<MahasiswaData>> {
        return listMahasiswa
    }

    fun getMahasiswaData() {
        val query = App.database.mahasiswaDao().getAllData()
        listMahasiswa.value = query
    }

    fun insertMahasiswaData(mahasiswa: MahasiswaData) {
        val query = App.database.mahasiswaDao().insertData(mahasiswa)
        getMahasiswaData()
    }

    fun editMahasiswaData(mahasiswa: MahasiswaData) {
        val query = App.database.mahasiswaDao().editData(mahasiswa)
        getMahasiswaData()
    }

    fun deleteMahasiswaById(mahasiswa: MahasiswaData) {
        val query = App.database.mahasiswaDao().deleteById(mahasiswa)
        getMahasiswaData()
    }

}