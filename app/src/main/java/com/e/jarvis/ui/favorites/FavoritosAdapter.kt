package com.e.jarvis.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_top_heroi.view.*

class FavoritosAdapter( val listener : FavoritosOnClickListener) : RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder>() {

    var listFavoritos = emptyList<Favorito>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        return FavoritosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_heroi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        val favorito = listFavoritos[position]

        val picasso = Picasso.get()

        if (favorito.tipoDoResult == "char") {
            //para char
            picasso.load(favorito.results.thumbnail?.path + "." + favorito.results.thumbnail?.extension)
                .into(holder.imagemFav)
            holder.nomeFav.text = favorito.results.name

        } else if (favorito.tipoDoResult == "comic" || favorito.tipoDoResult == "serie") {
            //para comic e series
            picasso.load(favorito.results.thumbnail?.path + "." + favorito.results.thumbnail?.extension)
                .into(holder.imagemFav)
            holder.nomeFav.text = favorito.results.title

        } else {
            //storie tem thumbnail null fica dando erro, por isso teve que fazer verificação
            if (favorito.results.thumbnail != null) {
                picasso.load(favorito.results.thumbnail.path + "." + favorito.results.thumbnail.extension)
                    .into(holder.imagemFav)
                holder.nomeFav.text = favorito.results.title
            } else {
                picasso.load("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available" + "." + "jpg")
                    .into(holder.imagemFav)
                holder.nomeFav.text = favorito.results.title
            }
        }

        holder.itemView.setOnLongClickListener {
            listener.selectFavorito(position)
            true
        }
    }

    override fun getItemCount() = listFavoritos.size


    interface FavoritosOnClickListener {
        fun selectFavorito(position: Int)
    }

    //passa as listas e avisa o adapter quando tem mudanças
    fun setData(favorito: List<Favorito>) {
        this.listFavoritos = favorito
        notifyDataSetChanged()

    }

    class FavoritosViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val imagemFav: ImageView = item.iv_personagem
        val nomeFav: TextView = item.tv_personagem

    }
}


