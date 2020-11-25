package com.e.jarvis.ui.exibe.story

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.e.jarvis.R
import kotlinx.android.synthetic.main.item_exibe.view.*

class ExibeStoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_stories, container, false)

        view.btn_exibe_stories.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_personagem_fragment)
        }

        view.btn_exibe_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_series_fragment)
        }

        view.btn_exibe_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_stories_to_comics_fragment)
        }
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}