package com.e.jarvis.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.jarvis.R
import com.e.jarvis.models.modelsfavoritos.Favorito
import kotlinx.android.synthetic.main.favoritos_dialog.view.*
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoritosFragment : Fragment(), FavoritosAdapter.FavoritosOnClickListener {

    private val viewModel: FavoritosViewModel by viewModel()
    lateinit var adapChar: FavoritosAdapter
     lateinit var adapComics: FavoritosAdapter



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
        //  val adapterChar = FavoritosAdapter(this)
       adapChar = FavoritosAdapter(this)
        rcv_favoritos_char.adapter = adapChar
        rcv_favoritos_char.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_char.setHasFixedSize(true)

        viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
            adapChar.setData(it)
        })
        viewModel.getAllCharsFavoritos()


        //recyclerview dos comics favoritos
        adapComics = FavoritosAdapter(this)
        rcv_favoritos_comics.adapter = adapComics
        rcv_favoritos_comics.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_comics.setHasFixedSize(true)

        viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
            adapComics.setData((it))
        })
        viewModel.getAllComicsFavoritos()


//        //recyclerview dos series favoritos
//        val adapterSeries = FavoritosAdapter(this)
//        rcv_favoritos_series.adapter = adapterSeries
//        rcv_favoritos_series.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        rcv_favoritos_series.setHasFixedSize(true)
//
//        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
//            adapterSeries.setData((it))
//        })
//        viewModel.getAllSeriesFavoritos()
//
//
//        //recyclerview dos stories favoritos
//        val adapterStories = FavoritosAdapter(this)
//        rcv_favoritos_stories.adapter = adapterStories
//        rcv_favoritos_stories.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        rcv_favoritos_stories.setHasFixedSize(true)
//
//        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
//            adapterStories.setData((it))
//        })
//        viewModel.getAllStoriesFavoritos()

    }

    override fun selectFavorito(position: Int) {



       val favoritoChar = adapChar.listFavoritos[position]

      val favoritoComic = adapComics.listFavoritos[position]

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->

            Log.i("FAVORITOSSSSS", favoritoChar.toString() + favoritoComic.toString())







        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Deletar Favorito dddd")
//            if(charFavorito.tipoDoResult == "char"){
//                builder.setMessage("Dejesa deletar o favorito ${charFavorito.results.name}?")
//            } else {
//                builder.setMessage("Dejesa deletar o favorito ${favorito.results.title}?")
//            }

        builder.create().show()

    }

    fun deletar(favorito: Favorito){

        if(favorito.tipoDoResult == "char"){
            viewModel.deleteFavorito(favorito)
            Toast.makeText(context, "Excluído de favoritos com sucesso", Toast.LENGTH_SHORT)
                .show()
            viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
                adapChar.setData(it)
            })
            viewModel.getAllCharsFavoritos()
        }

        if(favorito.tipoDoResult == "comic"){
            viewModel.deleteFavorito(favorito)
            Toast.makeText(context, "Excluído de favoritos com sucesso", Toast.LENGTH_SHORT)
                .show()
            viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
                adapComics.setData(it)
            })
            viewModel.getAllCharsFavoritos()

        }


    }


}




