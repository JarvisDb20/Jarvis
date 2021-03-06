package com.e.jarvis.ui.exibe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.di.viewModelModule
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage
import com.squareup.picasso.Picasso

class ExibeAdapter(
    val listener: onClickListener
) : RecyclerView.Adapter<ExibeAdapter.ViewHolder>() {

    var listImages: ArrayList<ItemImage> = arrayListOf(ItemImage(GenericImage("","") , ApiObject("",""),"invalid"))


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivExibe: ImageView = itemView.findViewById(R.id.iv_exibe_menu)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.resultClick(position)
        }

    }

    interface onClickListener {
        fun resultClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exibe_image, parent, false)
        return ViewHolder(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = listImages[position]
        val picasso = Picasso.get()
        if(currItem.thumb.path!="")
            picasso.load(currItem.thumb.path + "/portrait_uncanny." + currItem.thumb.extension)
                .into(holder.ivExibe)
        else{
            holder.ivExibe.setImageResource(R.drawable.invalid_reference)
        }
    }

    override fun getItemCount(): Int {
        return listImages.size
    }


    fun updateList(list: ArrayList<ItemImage>) {
        listImages = list
        notifyDataSetChanged()
    }
}