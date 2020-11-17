package com.e.jarvis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_exibe_comics.view.*
import kotlinx.android.synthetic.main.fragment_exibe_stories.view.*

class ExibeStoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_stories, container, false)

        view.btn_frag_stories_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_personagem_fragment)
        }

        view.btn_frag_stories_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_series_fragment)
        }

        view.btn_frag_stories_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_comics_fragment)
        }



        return view
    }


}