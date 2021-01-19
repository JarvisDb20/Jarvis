package com.e.jarvis.ui.exibe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import com.e.jarvis.ui.MainActivity
import com.squareup.picasso.Picasso

class ImageFullFragment : BaseFragment() {
    val args: ImageFullFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_full, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivFullImage = view.findViewById<ImageView>(R.id.iv_full_image)
        try {
            Picasso.get()
                .load(args.fullImage.thumb.path + "/portrait_incredible." + args.fullImage.thumb.extension)
                .into(ivFullImage)
            (activity as MainActivity).supportActionBar?.title = args.fullImage.name
        } catch (ex: Exception) {
            Toast.makeText(context, "Deu ruim", Toast.LENGTH_SHORT).show()
        }
    }
}