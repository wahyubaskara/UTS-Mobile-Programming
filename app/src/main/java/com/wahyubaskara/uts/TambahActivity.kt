package com.wahyubaskara.uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyubaskara.uts.database.models.MahasiswaData
import com.wahyubaskara.uts.repository.MahasiswaRepository
import kotlinx.android.synthetic.main.activity_tambah.*

class TambahActivity : AppCompatActivity() {
    private lateinit var repo: MahasiswaRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)
        title = "Tambah Mahasiswa"

        initObject()
        initEventListener()
    }

    private fun initObject() {
        repo = MahasiswaRepository.getInstance()
    }

    private fun initEventListener() {
        btnSave.setOnClickListener {
            //AMBIL DATA
            val nim = etNim.text.trim().toString()
            val nama = etNama.text.trim().toString()
            val jurusan = etJurusan.text.trim().toString()
            val telp = etTelp.text.trim().toString()
            val alamat = etAlamat.text.trim().toString()

            //VALIDASI DATA
            var isError = false
            if (nim.isEmpty()) {
                tilNim.error = "Wajib diisi"
                isError = true
            }
            if (nama.isEmpty()) {
                tilNama.error = "Wajib diisi"
                isError = true
            }
            if (jurusan.isEmpty()) {
                tilJurusan.error = "Wajib diisi"
                isError = true
            }
            if (telp.isEmpty()) {
                tilTelp.error = "Wajib diisi"
                isError = true
            }
            if (alamat.isEmpty()) {
                tilAlamat.error = "Wajib diisi"
                isError = true
            }

            //SAVE DATA
            if (!isError) {
                val data = MahasiswaData(0, nim, nama, jurusan, telp, alamat)
                repo.insertMahasiswaData(data)
                finish()
            }
        }
    }
}
