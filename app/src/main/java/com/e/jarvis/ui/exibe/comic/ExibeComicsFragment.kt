package com.e.jarvis.ui.exibe.comic

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.e.jarvis.R
import kotlinx.android.synthetic.main.item_exibe.view.*


class ExibeComicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_comics, container, false)

        view.btn_exibe_comics.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_personagem)
        }

        view.btn_exibe_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_series)
        }

        view.btn_exibe_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_comics_to_stories)
        }

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}