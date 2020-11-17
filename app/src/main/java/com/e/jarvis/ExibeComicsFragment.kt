package com.e.jarvis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_exibe_comics.view.*
import kotlinx.android.synthetic.main.fragment_exibe_personagem.view.*
import kotlinx.android.synthetic.main.fragment_exibe_personagem.view.btn_frag_personagem_char

class ExibeComicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_comics, container, false)

        view.btn_frag_comics_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_personagem)
        }

        view.btn_frag_comics_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_series )
        }

        view.btn_frag_comics_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_stories )
        }



        return view
    }


}