package com.e.jarvis.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.jarvis.R
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoritosFragment : BaseFragment(), FavoritosAdapter.FavoritosOnClickListener {


    private val viewModel: FavoritosViewModel by viewModel()
    lateinit var adapChar: FavoritosAdapter
    lateinit var adapComic: FavoritosAdapter
    lateinit var adapSerie: FavoritosAdapter
    lateinit var adapStorie: FavoritosAdapter


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
        adapChar = FavoritosAdapter(this, "char")
        rcv_favoritos_char.adapter = adapChar
        rcv_favoritos_char.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_char.setHasFixedSize(true)

        viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
            adapChar.setData(it)
        })
        viewModel.getAllCharsFavoritos()

        //recyclerview dos comics favoritos
        adapComic = FavoritosAdapter(this, "comic")
        rcv_favoritos_comics.adapter = adapComic
        rcv_favoritos_comics.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_comics.setHasFixedSize(true)

        viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
            adapComic.setData((it))
        })
        viewModel.getAllComicsFavoritos()

        //recyclerview dos series favoritos
        adapSerie = FavoritosAdapter(this, "serie")
        rcv_favoritos_series.adapter = adapSerie
        rcv_favoritos_series.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_series.setHasFixedSize(true)

        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
            adapSerie.setData((it))
        })
        viewModel.getAllSeriesFavoritos()

        //recyclerview dos stories favoritos
        adapStorie = FavoritosAdapter(this, "storie")
        rcv_favoritos_stories.adapter = adapStorie
        rcv_favoritos_stories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_stories.setHasFixedSize(true)

        viewModel.listStoriesFavoritos.observe(viewLifecycleOwner, {
            adapStorie.setData((it))
        })
        viewModel.getAllStoriesFavoritos()

    }

    override fun selectFavorito(position: Int, origin: String) {
        val favorito = when (origin) {
            "char" -> {
                adapChar.listFavoritos[position]
            }
            "comic" -> {
                adapComic.listFavoritos[position]
            }
            "serie" -> {
                adapSerie.listFavoritos[position]
            }
            else -> adapStorie.listFavoritos[position]
        }

        //criando o alert dialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            deletar(favorito)
        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Deletar Favorito?")
        if (origin == "char") {
            builder.setMessage("Deseja deletar o favorito ${favorito.results.name}?")
        } else {
            builder.setMessage("Deseja deletar o favorito ${favorito.results.title}?")
        }
        builder.create().show()


    }

    fun deletar(favorito: Favorito) {
        when (favorito.tipoDoResult) {
            "char" -> {
                viewModel.deleteFavoritoChar(favorito)
            }
            "comic" -> {
                viewModel.deleteFavoritoComic(favorito)
            }
            "serie" -> {
                viewModel.deleteFavoritoSerie(favorito)
            }
            "storie" -> {
                viewModel.deleteFavoritoStorie(favorito)
            }
        }

        Toast.makeText(context, "Excluído de favoritos com sucesso", Toast.LENGTH_SHORT)
            .show()


//        if (favorito.tipoDoResult == "char") {
//            viewModel.deleteFavoritoChar(favorito)
//        }
//
//        if (favorito.tipoDoResult == "comic") {
//            viewModel.deleteFavoritoComic(favorito)
//        }
//
//        if (favorito.tipoDoResult == "comic") {
//            viewModel.deleteFavoritoComic(favorito)
//        }

    }
}






