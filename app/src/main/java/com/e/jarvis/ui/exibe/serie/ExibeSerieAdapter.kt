package com.e.jarvis.ui.exibe.serie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.utils.ItemImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_exibe_image.view.*

class ExibeSerieAdapter(
    var listImagensSeries: ArrayList<ItemImage>,
    val listener: serieOnClickListener
) : RecyclerView.Adapter<ExibeSerieAdapter.SerieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_exibe_image, parent, false)
        return SerieViewHolder(item)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val objetoSerie = listImagensSeries[position]
        var picasso = Picasso.get()
        picasso.load(listImagensSeries[position].thumb.path + "/landscape_incredible." + listImagensSeries[position].thumb.extension)
            .into(holder.imagem)

        holder.itemView.setOnClickListener {
            listener.serieClick(position)
        }

    }


    override fun getItemCount() = listImagensSeries.size

    inner class SerieViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imagem: ImageView = item.iv_exibe_menu

    }


    interface serieOnClickListener {
        fun serieClick(position: Int)
    }

    fun updateList(list: ArrayList<ItemImage>){
        listImagensSeries = list
        notifyDataSetChanged()
    }

}