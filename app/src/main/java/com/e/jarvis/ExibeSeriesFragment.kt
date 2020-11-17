package com.e.jarvis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_exibe_personagem.view.*
import kotlinx.android.synthetic.main.fragment_exibe_series_fragment.view.*

class ExibeSeriesFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_series_fragment, container, false)

        view.btn_frag_series_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_personagem_fragment)
        }

        view.btn_frag_series_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_comics_fragment)
        }

        view.btn_frag_series_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_stories_fragment)
        }




        return view
    }


}