package com.e.jarvis.ui.exibe.comic

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.e.jarvis.MainActivity
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage
//import com.e.jarvis.repository.service
import com.e.jarvis.ui.exibe.chars.ExibeCharFragmentDirections
import com.e.jarvis.ui.exibe.chars.ExibeCharViewModel
import kotlinx.android.synthetic.main.fragment_exibe_comics.view.*
import kotlinx.android.synthetic.main.fragment_exibe_series.view.*
import kotlinx.android.synthetic.main.item_exibe_botoes.view.*
import kotlinx.android.synthetic.main.item_exibe_circle_viewpager.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ExibeComicsFragment : Fragment(), ExibeComicsAdapter.comicOnClickListener {

    //instancia viewModel
    private val viewModel: ExibeComicsViewModel by viewModel()


    //classe do generated que tem o args que o frag comic vai receber
    val args: ExibeComicsFragmentArgs by navArgs()


    //lista dos comics
    lateinit var listComics: ArrayList<GenericResults>

    //variaveis do viewpager
    var layoutStarted = false

    //lista apenas as imagens dos comics
    lateinit var listImages: ArrayList<ItemImage>

    lateinit var adapter: ExibeComicsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_comics, container, false)

        view.btn_exibe_comics.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_char.setOnClickListener {
            val passaArgsComic =
                ExibeComicsFragmentDirections.navigateComicsToPersonagem(args.apiObj)
            findNavController().navigate(passaArgsComic)
        }

        view.btn_exibe_series.setOnClickListener {
            val passaArgsComic =
                ExibeComicsFragmentDirections.navigateComicsToSeries(args.apiObj)
            findNavController().navigate(passaArgsComic)
        }

        view.btn_exibe_stories.setOnClickListener {
            val passaArgsComic = ExibeComicsFragmentDirections.navigateComicsToStories(args.apiObj)
            findNavController().navigate(passaArgsComic)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun comicClick(position: Int) {
        val directions =
            ExibeComicsFragmentDirections.actionExibeComicsFragmentToImageFullFragment(
                ItemImage(
                    listImages[position].thumb,
                    args.apiObj,
                    listComics[position].title!!
                )
            )
        findNavController().navigate(directions)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recebendo os args pra ver que tipo é e então chamar a função adequada
        val objeto = args.apiObj
        Log.i("OBJETO CHEGOU COMICS", objeto.toString())

        when (objeto.tipoId) {
            "char" -> {
                viewModel.getComicChar(objeto.id)
            }
            "comic" -> {
                viewModel.getComic(objeto.id)
            }
            "series" -> {
                viewModel.getComicSeries(objeto.id)
            }
            "stories" -> {
                viewModel.getComicStories(objeto.id)
            }
        }

        //para viewpager
        val vp = view.vp_images
        val indicator = view.ci_images

        viewModel.comic.observe(viewLifecycleOwner, {
            if (it.size != 0) {
                exibeInfo(view, it[0])
                listComics = it

                it.forEach { linha ->

                    if (linha.thumbnail != null) {
                        listImages.add(
                            ItemImage(
                                linha.thumbnail,
                                ApiObject(
                                    "char",
                                    linha.id
                                )
                            )
                        )


                    } else {
                        listImages.add(
                            ItemImage(
                                GenericImage(
                                    "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
                                    "jpg"
                                ),
                                ApiObject(
                                    "char",
                                    linha.id
                                )
                            )
                        )
                    }


                }
                adapter.updateList(listImages)
                if (indicator.isEmpty())
                    indicator.setViewPager(vp)


            } else {
                (activity as MainActivity).supportActionBar?.title = "Not found..."
                view.tv_titulo_frag_comics.text = "Not found..."
                view.tv_descricao_frag_comics.text = "No description found..."

                view.vp_images.setBackgroundColor(Color.GRAY)
            }

        })


        //flag do layout
        if (!layoutStarted) {
            listImages = arrayListOf()
            adapter = ExibeComicsAdapter(listImages, this)
            layoutStarted = true
        }

        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //atualiza info quando muda a imagem do vp
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                exibeInfo(view, listComics[position])
                super.onPageSelected(position)
            }
        })


    }


    fun exibeInfo(view: View, res: GenericResults) {
        (activity as MainActivity).supportActionBar?.title = res.title
        view.tv_titulo_frag_comics.text = res.title
        if (res.description == null || res.description == " ")
            view.tv_descricao_frag_comics.text = "No description found..."
        else
            view.tv_descricao_frag_comics.text = res.description

    }
}