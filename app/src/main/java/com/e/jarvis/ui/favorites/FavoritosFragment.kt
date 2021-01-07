package com.e.jarvis.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.jarvis.R
import com.e.jarvis.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoritosFragment : Fragment() {

    private val viewModel: FavoritosViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //recyclerview dos chars favoritos
        val adapterChar = FavoritosAdapter()
        rcv_favoritos_char.adapter = adapterChar
        rcv_favoritos_char.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_char.setHasFixedSize(true)

        viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
            adapterChar.setData(it)
        })
        viewModel.getAllCharsFavoritos()



        //recyclerview dos comics favoritos
        val adapterComics = FavoritosAdapter()
        rcv_favoritos_comics.adapter = adapterComics
        rcv_favoritos_comics.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_comics.setHasFixedSize(true)

        viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
            adapterComics.setData((it))
        })
        viewModel.getAllComicsFavoritos()



        //recyclerview dos series favoritos
        val adapterSeries = FavoritosAdapter()
        rcv_favoritos_series.adapter = adapterSeries
        rcv_favoritos_series.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_series.setHasFixedSize(true)

        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
            adapterSeries.setData((it))
        })
        viewModel.getAllSeriesFavoritos()


        //recyclerview dos stories favoritos
        val adapterStories = FavoritosAdapter()
        rcv_favoritos_stories.adapter = adapterStories
        rcv_favoritos_stories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_stories.setHasFixedSize(true)

        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
            adapterStories.setData((it))
        })
        viewModel.getAllStoriesFavoritos()















    }


}




