package com.e.jarvis.ui.favorites


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.jarvis.R

import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.ui.BaseFragment
import com.e.jarvis.ui.dialog.DialogFragmentDelete
import kotlinx.android.synthetic.main.favoritos_dialog.view.*
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoritosFragment : BaseFragment(), FavoritosAdapter.FavoritosOnClickListener,
        DialogFragmentDelete.NoticeDialogListener {


    private val viewModel: FavoritosViewModel by viewModel()

    lateinit var adapChar: FavoritosAdapter
    lateinit var adapComic: FavoritosAdapter
    lateinit var adapSerie: FavoritosAdapter
    lateinit var adapStorie: FavoritosAdapter

    var listaAdapChar : List<Favorito>? = listOf<Favorito>()
    var listaAdapComic : List<Favorito>?= listOf<Favorito>()
    var listaAdapSerie: List<Favorito>? = listOf<Favorito>()
    var listaAdapStorie: List<Favorito>? = listOf<Favorito>()


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


        viewModel.getAllCharsFavoritos()
        viewModel.getAllComicsFavoritos()
        viewModel.getAllSeriesFavoritos()
        viewModel.getAllStoriesFavoritos()



        viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
            adapChar.setData(it.data)
            listaAdapChar = it.data
        })


        //recyclerview dos comics favoritos
        adapComic = FavoritosAdapter(this, "comic")
        rcv_favoritos_comics.adapter = adapComic
        rcv_favoritos_comics.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_comics.setHasFixedSize(true)

        viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
            adapComic.setData(it.data)
            listaAdapComic = it.data
        })


        //recyclerview dos series favoritos
        adapSerie = FavoritosAdapter(this, "serie")
        rcv_favoritos_series.adapter = adapSerie
        rcv_favoritos_series.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_series.setHasFixedSize(true)

        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
            adapSerie.setData(it.data)
            listaAdapSerie = it.data
        })


        //recyclerview dos stories favoritos
        adapStorie = FavoritosAdapter(this, "storie")
        rcv_favoritos_stories.adapter = adapStorie
        rcv_favoritos_stories.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_stories.setHasFixedSize(true)

        viewModel.listStoriesFavoritos.observe(viewLifecycleOwner, {
            adapStorie.setData(it.data)
            listaAdapStorie = it.data
        })

    }

    override fun selectFavorito(position: Int, origin: String) {
        val favorito = when (origin) {
            "char" -> {
                adapChar.listFavoritos!![position]
            }
            "comic" -> {
                adapComic.listFavoritos!![position]
            }
            "serie" -> {
                adapSerie.listFavoritos!![position]
            }
            else -> adapStorie.listFavoritos!![position]
        }

        val dialogFrag = DialogFragmentDelete(this)
        val bundle = Bundle()
        bundle.putSerializable("key", favorito)
        dialogFrag.arguments = bundle
        dialogFrag.show(childFragmentManager, "dialog")
    }


    override fun onDialogDeleteClick(dialog: DialogFragment, favorito: Favorito) {
        Log.i("NO FRAGMENTE FAVORITOS", favorito.toString())
        viewModel.deleteFavorito(favorito)
        when (favorito.tipoDoResult) {
            "char" -> {
                adapChar.setData(listaAdapChar)
            }
            "comic" -> {
                adapComic.setData(listaAdapComic)
            }
            "serie" -> {
                adapSerie.setData(listaAdapSerie)
            }
            "storie" -> {
                adapStorie.setData(listaAdapStorie)
            }
        }
        Toast.makeText(context, "Favorito deletado com sucesso", Toast.LENGTH_LONG).show()
    }

    override fun onDialogVerInfo(dialog: DialogFragment, objFavorito: Favorito) {
        Log.i("NO FRAGMENTE FAVORITOS", "vai chamar exibe pra mostrar info ")
        sharedModel.setSelectedResult(objFavorito.results)

        when (objFavorito.tipoDoResult) {
            "char" -> {
                findNavController().navigate(R.id.action_favoritosFragment_to_exibePersonagemFragment)
            }
            "comic" -> {
                findNavController().navigate(R.id.action_favoritosFragment_to_exibeComicsFragment2)
            }
            "serie" -> {
                findNavController().navigate(R.id.action_favoritosFragment_to_exibeSeriesFragement)
            }
            "storie" -> {
                findNavController().navigate(R.id.action_favoritosFragment_to_exibeStoriesFragment)
            }

        }
    }
}









