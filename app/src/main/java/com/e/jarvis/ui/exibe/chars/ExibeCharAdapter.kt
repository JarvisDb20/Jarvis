package com.e.jarvis.ui.exibe.chars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.utils.ItemImage
import com.squareup.picasso.Picasso

class ExibeCharAdapter(
    var listImages: ArrayList<ItemImage>,
    val listener: onClickListener
) : RecyclerView.Adapter<ExibeCharAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivExibe : ImageView = itemView.findViewById(R.id.iv_exibe_menu)

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
            .inflate(R.layout.item_exibe_image, parent, false)
        return ViewHolder(layoutItemRest)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var picasso = Picasso.get()
        picasso.load(listImages[position].thumb.path + "/landscape_incredible." +listImages[position].thumb.extension).into(holder.ivExibe)

    }

    override fun getItemCount(): Int {
        return listImages.size
    }


    fun updateList(list: ArrayList<ItemImage>){
        listImages = list
        notifyDataSetChanged()
    }
}