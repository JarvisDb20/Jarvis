package com.e.jarvis.ui.exibe.comic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.utils.ItemImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_exibe_image.view.*

class ExibeComicsAdapter(
    var listImagensComics: ArrayList<ItemImage>,
    val listener: comicOnClickListener
) : RecyclerView.Adapter<ExibeComicsAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_exibe_image, parent, false)
        return ComicViewHolder(item)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = listImagensComics[position]
        val picasso = Picasso.get()
        picasso.load(comic.thumb.path + "/landscape_incredible." + comic.thumb.extension)
            .into(holder.imagemComic)

        holder.itemView.setOnClickListener {
            listener.comicClick(position)
        }

    }

    override fun getItemCount() = listImagensComics.size

    interface comicOnClickListener {
        fun comicClick(position: Int)
    }

    inner class ComicViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imagemComic: ImageView = item.iv_exibe_menu
    }


    fun updateList(list: ArrayList<ItemImage>) {
        listImagensComics = list
        notifyDataSetChanged()
    }
}