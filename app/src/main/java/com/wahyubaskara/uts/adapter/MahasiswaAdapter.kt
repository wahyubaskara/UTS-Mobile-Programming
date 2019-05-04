package com.wahyubaskara.uts.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.wahyubaskara.uts.EditActivity
import com.wahyubaskara.uts.R
import com.wahyubaskara.uts.database.models.MahasiswaData
import com.wahyubaskara.uts.repository.MahasiswaRepository
import kotlinx.android.synthetic.main.item_mahasiswa.view.*




class MahasiswaAdapter(private val context: Context, private val list: List<MahasiswaData>): RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem = list[position]
        holder.tvNama.text = currentitem.nama
        holder.tvNim.text = currentitem.nim
        holder.tvJurusan.text = currentitem.jurusan
        holder.tvTelp.text = currentitem.telp
        holder.tvAlamat.text = currentitem.alamat
        holder.card.setOnClickListener { showDialog(position) }
    }

    private fun showDialog(position: Int) {
        val items = arrayOf("Edit", "Delete")
        val builder = AlertDialog.Builder(context)
        builder.setTitle(list[position].nama)
        builder.setItems(items) { _, item ->
            when (item) {
                0 -> {
                    val i = Intent(context, EditActivity::class.java)
                    i.putExtra("id", list[position].id.toString())
                    i.putExtra("nim", list[position].nim)
                    i.putExtra("nama", list[position].nama)
                    i.putExtra("jurusan", list[position].jurusan)
                    i.putExtra("telp", list[position].telp)
                    i.putExtra("alamat", list[position].alamat)
                    context.startActivity(i)
                }
                1 -> {
                    val repo = MahasiswaRepository.getInstance()
                    repo.deleteMahasiswaById(list[position])
                }
            }
        }
        builder.create().show()
    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.tvNama
        val tvNim: TextView = itemView.tvNim
        val tvJurusan: TextView = itemView.tvJurusan
        val tvTelp: TextView = itemView.tvTelp
        val tvAlamat: TextView = itemView.tvAlamat
        val card: MaterialCardView = itemView.cardView
    }

}