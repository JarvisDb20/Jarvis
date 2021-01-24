package com.e.jarvis.ui.pesquisa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.squareup.picasso.Picasso


class PesquisaAdapter(
    var listSearches: ArrayList<GenericResults>,
    val listener: onClickListener
) : RecyclerView.Adapter<PesquisaAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivExibe: ImageView = itemView.findViewById(R.id.iv_personagem)
        val tvNome : TextView = itemView.findViewById(R.id.tv_nome_personagem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                v?.let { listener.searchClick(position, it) }
        }

    }

    interface onClickListener {
        fun searchClick(position: Int, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutItemRest = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_heroi, parent, false)
        return ViewHolder(layoutItemRest)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picasso = Picasso.get()
        val currentItem = listSearches[position]
        picasso.load(currentItem.thumbnail?.path + "/landscape_incredible." + currentItem.thumbnail?.extension)
            .into(holder.ivExibe)
        if (currentItem.name == "" || currentItem.name == null)
            holder.tvNome.text = currentItem.title
        else
            holder.tvNome.text = currentItem.name

    }

    override fun getItemCount(): Int {
        return listSearches.size
    }


    fun updateList(list: ArrayList<GenericResults>) {
        listSearches = list
        notifyDataSetChanged()
    }
}