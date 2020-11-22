package com.e.jarvis.ui.exibe.serie

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.e.jarvis.R
import kotlinx.android.synthetic.main.item_exibe.view.*

class ExibeSeriesFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_series, container, false)

        view.btn_exibe_series.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_char.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_personagem_fragment)
        }

        view.btn_exibe_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_comics_fragment)
        }

        view.btn_exibe_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_series_to_stories_fragment)
        }

        return view
    }


}