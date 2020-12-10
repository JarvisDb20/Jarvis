package com.e.jarvis.ui.exibe.serie

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
import com.e.jarvis.repository.service
import kotlinx.android.synthetic.main.fragment_exibe_series.*
import kotlinx.android.synthetic.main.fragment_exibe_series.view.*
import kotlinx.android.synthetic.main.item_exibe_botoes.view.*
import kotlinx.android.synthetic.main.item_exibe_circle_viewpager.view.*


class ExibeSeriesFragement : Fragment(), ExibeSerieAdapter.serieOnClickListener {

    private val viewModel by viewModels<ExibeSerieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ExibeSerieViewModel(service) as T
            }
        }
    }

    //classe do generated
    val args: ExibeSeriesFragementArgs by navArgs()

    lateinit var listSeries: ArrayList<GenericResults>

    //variaveis para o viewpager
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
            val passaArgsSeries =
                ExibeSeriesFragementDirections.navigateSeriesToPersonagemFragment(args.apiObj)
            findNavController().navigate(passaArgsSeries)
        }

        view.btn_exibe_comics.setOnClickListener {
            val passaArgsSeries =
                ExibeSeriesFragementDirections.navigateSeriesToComicsFragment(args.apiObj)
            findNavController().navigate(passaArgsSeries)
        }

        view.btn_exibe_stories.setOnClickListener {
            val passaArgsSeries =
                ExibeSeriesFragementDirections.navigateSeriesToStoriesFragment(args.apiObj)
            findNavController().navigate(passaArgsSeries)
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

        //recebendo os args
        val objeto = args.apiObj
        Log.i("OBJETO CHEGOU SERIES", objeto.toString())

        when (objeto.tipoId) {
            "char" -> {
                viewModel.getSeriesChar(objeto.id)
            }
            "comic" -> {
                tv_descricao_frag_series.setText("NOT FOUND. We don´t see this comming.")
            }
            "series" -> {
                viewModel.getSerie(objeto.id)
            }
            "stories" -> {
                viewModel.getSeriesStories(objeto.id)
            }

        }

        //-----> não entendi essa parte direito e no linha do char e do serie tá vermelho
        //configurando viewpager
        val vp = view.vp_images
        val indicator = view.ci_images
        viewModel.serie.observe(viewLifecycleOwner, {

            if (it.size != 0) {
                exibeInfo(view, it[0])
                listSeries = it

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
                view.tv_titulo_frag_series.text = "Not found..."
                view.tv_descricao_frag_series.text = "No description found..."

                view.vp_images.setBackgroundColor(Color.GRAY)
            }


        })


        //Lucas, vc pode me explicar melhor essa coisa de flag por favor? :)
        //só entra aqui uma vez, pq aqui que ele começa a existir
        if (!layoutStarted) {
            listImages = arrayListOf()
            adapter = ExibeSerieAdapter(listImages, this)
            layoutStarted = true
        }

        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //atualizar as info, girar página
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                exibeInfo(view, listSeries[position])
                super.onPageSelected(position)
            }

        })


    }


    fun exibeInfo(view: View, res: GenericResults) {
        (activity as MainActivity).supportActionBar?.title = res.title
        view.tv_titulo_frag_series.text = res.title
        if (res.description == null || res.description == "")
            view.tv_descricao_frag_series.text = "No description found..."
        else
            view.tv_descricao_frag_series.text = res.description
    }
}