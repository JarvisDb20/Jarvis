package com.e.jarvis.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.squareup.picasso.Picasso

class HomeAdapter (
        private var chars: ArrayList<GenericResults>,
        val listener: onClickListener
        ): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        val ivChar : ImageView = itemView.findViewById(R.id.iv_personagem)
        val tvChar : TextView = itemView.findViewById(R.id.tv_personagem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.charsClick(position)
        }
    }
    interface onClickListener {
        fun charsClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutItemRest = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_heroi, parent, false)
        return ViewHolder(layoutItemRest)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var picasso = Picasso.get()
        var currentItem = chars[position]
        picasso.load(currentItem.thumbnail.path + "." + currentItem.thumbnail.extension).into(holder.ivChar)
        holder.tvChar.text = currentItem.name

    }
    override fun getItemCount(): Int {
        return chars.size
    }
    fun updateChars(char: ArrayList<GenericResults>){
        chars = char
        notifyDataSetChanged()
    }

}