package com.example.motivation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.motivation.model.NoticiaItem
import kotlinx.android.synthetic.main.news_item_activity.view.*
import org.jetbrains.anko.toast

class NoticiaAdapter(
    private val context: Context,
    private var noticiaList: ArrayList<NoticiaItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var noticia = noticiaList[position]

        val titulo = holder.itemView.lblTitulo
        val fonte = holder.itemView.lblFonte
        val resumo = holder.itemView.lblResumo
        val url = noticia.link



        titulo.text = noticia.titulo
        fonte.text = noticia.fonte
        resumo.text = noticia.resumo


        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item_activity, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = noticiaList.size
}


class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)

