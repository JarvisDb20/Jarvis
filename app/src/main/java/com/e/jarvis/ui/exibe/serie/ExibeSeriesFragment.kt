package com.e.jarvis.ui.exibe.serie

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.e.jarvis.R
import com.e.jarvis.models.chars.Results
import com.e.jarvis.models.utils.ItemImage
import com.e.jarvis.repository.service
import com.e.jarvis.ui.exibe.chars.ExibeCharAdapter
import com.e.jarvis.ui.exibe.chars.ExibeCharFragmentArgs
import com.e.jarvis.ui.exibe.chars.ExibeCharViewModel
import kotlinx.android.synthetic.main.item_exibe.view.*

class ExibeSeriesFragement : Fragment(), ExibeSerieAdapter.serieOnClickListener {

    private val viewModel by viewModels<ExibeSerieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ExibeSerieViewModel(service) as T
            }
        }
    }

    //classe do generated
    val args: ExibeSeriesFragementDirections by navArgs()


    lateinit var listSeries: ArrayList<Results>

    //viriaveis para o viewpager
    var layoutStarted = false
    lateinit var listImages: ArrayList<ItemImage>
    lateinit var adapter: ExibeSerieAdapter
    //lateinit var gManager: GridLayoutManager


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
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun serieClick(position: Int) {
        TODO("Not yet implemented")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)











    }
}