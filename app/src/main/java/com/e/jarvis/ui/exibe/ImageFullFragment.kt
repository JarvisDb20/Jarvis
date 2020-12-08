package com.e.jarvis.ui.exibe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.e.jarvis.R
import com.squareup.picasso.Picasso

class ImageFullFragment : Fragment() {
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
            Picasso.get().load(args.fullImage.path + "/portrait_incredible." + args.fullImage.extension).into(ivFullImage)
        }catch (ex : Exception){
            Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show()
        }
    }
}