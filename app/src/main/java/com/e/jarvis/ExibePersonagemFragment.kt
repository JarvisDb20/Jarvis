package com.e.jarvis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_exibe_personagem.view.*

class ExibePersonagemFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_personagem, container, false)

        view.btn_frag_personagem_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_series_fragment)
        }

        view.btn_frag_personagem_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_comics_fragment)
        }

        view.btn_frag_personagem_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_stories_fragment)
        }


        return view


    }


}