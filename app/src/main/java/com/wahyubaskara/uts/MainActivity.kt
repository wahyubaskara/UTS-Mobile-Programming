package com.wahyubaskara.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahyubaskara.uts.adapter.MahasiswaAdapter
import com.wahyubaskara.uts.database.models.MahasiswaData
import com.wahyubaskara.uts.repository.MahasiswaRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var repo: MahasiswaRepository
    private lateinit var listMahasiswa: MutableList<MahasiswaData>
    private lateinit var adapterMahasiswa: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "List Mahasiswa"

        initObject()
        initRecyclerView()
        initEventListener()
        observeMahasiswaData()
        getMahasiswaData()
    }

    private fun initObject() {
        repo = MahasiswaRepository.getInstance()
        listMahasiswa = mutableListOf()
        adapterMahasiswa = MahasiswaAdapter(this, listMahasiswa)
    }

    private fun initRecyclerView() {
        val rvMahasiswa: RecyclerView = rvMahasiswa
        rvMahasiswa.apply {
            addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
            adapter = adapterMahasiswa
        }
    }

    private fun initEventListener() {
        swipeRefresh.setOnRefreshListener {
            getMahasiswaData()
        }

        fabTambah.setOnClickListener {
            val i = Intent(this, TambahActivity::class.java)
            startActivity(i)
        }
    }

    private fun getMahasiswaData() {
        repo.getMahasiswaData()
    }

    private fun observeMahasiswaData() {
        repo.subscribe().observe(this, Observer { data ->
            swipeRefresh.isRefreshing = false
            if (data.isEmpty()) {
                layoutEmpty.visibility = View.VISIBLE
            } else {
                layoutEmpty.visibility = View.GONE
            }
            listMahasiswa.clear()
            listMahasiswa.addAll(data.toMutableList())
            adapterMahasiswa.notifyDataSetChanged()
        })
    }
}
