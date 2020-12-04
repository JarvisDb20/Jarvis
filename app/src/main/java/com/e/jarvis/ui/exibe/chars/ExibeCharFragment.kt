package com.e.jarvis.ui.exibe.chars

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.e.jarvis.R
import com.e.jarvis.models.chars.Results
import com.e.jarvis.repository.service
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_exibe_char.view.*
import kotlinx.android.synthetic.main.item_exibe.*
import kotlinx.android.synthetic.main.item_exibe.view.*


class ExibeCharFragment : Fragment() {

    private val viewModel by viewModels<ExibeCharViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ExibeCharViewModel(service) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_char, container, false)


        //estou usando esse id para testes como se ele tivesse sido passado pra cá atraves do fragment pesquisa
        val id = "1009351"


        val picasso = Picasso.get()


        viewModel.getChar(id)
        viewModel.char.observe(viewLifecycleOwner, {
            view.tv_titulo_frag_char.text = it[0].name
            view.tv_descricao_frag_char.text = it[0].description
            val imageUrl =
                it[0].thumbnail.path + "/landscape_medium." + it[0].thumbnail.extension
            picasso.load(imageUrl).into(iv_exibe_menu)

            Log.i("EXIBECHARFRAGMENT", it[0].thumbnail.toString())
        })













       view.btn_exibe_char.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_series.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_series_fragment, )
        }

        view.btn_exibe_comics.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_comics_fragment)
        }

        view.btn_exibe_stories.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_personagem_to_exibe_stories_fragment)
        }
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}