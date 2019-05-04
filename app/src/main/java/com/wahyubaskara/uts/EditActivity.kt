package com.wahyubaskara.uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyubaskara.uts.database.models.MahasiswaData
import com.wahyubaskara.uts.repository.MahasiswaRepository
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    private lateinit var repo: MahasiswaRepository
    private lateinit var id: String
    private lateinit var nim: String
    private lateinit var nama: String
    private lateinit var jurusan: String
    private lateinit var telp: String
    private lateinit var alamat: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title = "Edit Mahasiswa"

        initObject()
        getExtras()
        setDefaultData()
        initEventListener()
    }

    private fun initObject() {
        repo = MahasiswaRepository.getInstance()
    }

    private fun getExtras() {
        id = intent.getStringExtra("id")
        nim = intent.getStringExtra("nim")
        nama = intent.getStringExtra("nama")
        jurusan = intent.getStringExtra("jurusan")
        telp = intent.getStringExtra("telp")
        alamat = intent.getStringExtra("alamat")
    }

    private fun setDefaultData() {
        etNim.setText(nim)
        etNama.setText(nama)
        etJurusan.setText(jurusan)
        etTelp.setText(telp)
        etAlamat.setText(alamat)
    }

    private fun initEventListener() {
        btnSave.setOnClickListener {
            //AMBIL DATA
            val newNim = etNim.text.trim().toString()
            val newNama = etNama.text.trim().toString()
            val newJurusan = etJurusan.text.trim().toString()
            val newTelp = etTelp.text.trim().toString()
            val newAlamat = etAlamat.text.trim().toString()

            //VALIDASI DATA
            var isError = false
            if (newNim.isEmpty()) {
                tilNim.error = "Wajib diisi"
                isError = true
            }
            if (newNama.isEmpty()) {
                tilNama.error = "Wajib diisi"
                isError = true
            }
            if (newJurusan.isEmpty()) {
                tilJurusan.error = "Wajib diisi"
                isError = true
            }
            if (newTelp.isEmpty()) {
                tilTelp.error = "Wajib diisi"
                isError = true
            }
            if (newAlamat.isEmpty()) {
                tilAlamat.error = "Wajib diisi"
                isError = true
            }

            //SAVE DATA
            if (!isError) {
                val data = MahasiswaData(id.toInt(), newNim, newNama, newJurusan, newTelp, newAlamat)
                repo.editMahasiswaData(data)
                finish()
            }
        }
    }
}
