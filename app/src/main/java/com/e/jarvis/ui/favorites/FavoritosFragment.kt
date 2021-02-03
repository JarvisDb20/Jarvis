package com.e.jarvis.ui.favorites


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
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


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listaAdapChar = arrayListOf<Favorito>()
        val listaAdapComic = arrayListOf<Favorito>()
        val listaAdapSerie = arrayListOf<Favorito>()
        val listaAdapStorie = arrayListOf<Favorito>()

        //recyclerview dos chars favoritos
        adapChar = FavoritosAdapter(this, "char")
        rcv_favoritos_char.adapter = adapChar
        rcv_favoritos_char.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_char.setHasFixedSize(true)

        viewModel.listCharsFavoritos.observe(viewLifecycleOwner, {
            it.data?.forEach {
                listaAdapChar.add(it)
            }
            adapChar.setData(listaAdapChar)
        })
        viewModel.getAllCharsFavoritos()


        //recyclerview dos comics favoritos
        adapComic = FavoritosAdapter(this, "comic")
        rcv_favoritos_comics.adapter = adapComic
        rcv_favoritos_comics.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_comics.setHasFixedSize(true)

        viewModel.listComicsFavoritos.observe(viewLifecycleOwner, {
            it.data?.forEach {
                listaAdapComic.add(it)
            }
            adapComic.setData(listaAdapComic)

        })
        viewModel.getAllComicsFavoritos()

        //recyclerview dos series favoritos
        adapSerie = FavoritosAdapter(this, "serie")
        rcv_favoritos_series.adapter = adapSerie
        rcv_favoritos_series.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_series.setHasFixedSize(true)

        viewModel.listSeriesFavoritos.observe(viewLifecycleOwner, {
            it.data?.forEach {
                listaAdapSerie.add(it)
                Log.i("FAVORITOS SERIES", listaAdapSerie.toString())
            }
            adapSerie.setData(listaAdapSerie)

        })
        viewModel.getAllSeriesFavoritos()

        //recyclerview dos stories favoritos
        adapStorie = FavoritosAdapter(this, "storie")
        rcv_favoritos_stories.adapter = adapStorie
        rcv_favoritos_stories.layoutManager =

            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_favoritos_stories.setHasFixedSize(true)

        viewModel.listStoriesFavoritos.observe(viewLifecycleOwner, {
            it.data?.forEach {
                listaAdapStorie.add(it)
                Log.i("FAVORITOS STORIES", listaAdapStorie.toString())
            }
            adapStorie.setData(listaAdapStorie)
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

        val dialogFrag = DialogFragmentDelete(this)
        val bundle = Bundle()
        bundle.putSerializable("key", favorito)
        dialogFrag.arguments = bundle
        dialogFrag.show(childFragmentManager, "dialog")
    }


    override fun onDialogDeleteClick(dialog: DialogFragment, favorito: Favorito) {
        Log.i("NO FRAGMENTE FAVORITOS", favorito.toString())
        viewModel.deleteFavorito(favorito)
        Toast.makeText(context, "Favorito deletado com sucesso", Toast.LENGTH_LONG).show()
    }

    override fun onDialogVerInfo(dialog: DialogFragment, objFavorito: Favorito) {
        Log.i("NO FRAGMENTE FAVORITOS", "vai chamar exibe pra mostrar info ")

    }
}









