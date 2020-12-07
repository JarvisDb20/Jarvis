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
import androidx.viewpager2.widget.ViewPager2
import com.e.jarvis.MainActivity
import com.e.jarvis.R
import com.e.jarvis.models.series.Results
import com.e.jarvis.models.utils.ItemImageChar
import com.e.jarvis.models.utils.apiObject
import com.e.jarvis.repository.service
import kotlinx.android.synthetic.main.fragment_exibe_series.*
import kotlinx.android.synthetic.main.fragment_exibe_series.view.*
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
    val args: ExibeSeriesFragementArgs by navArgs()

    lateinit var listSeries: ArrayList<Results>

    //variaveis para o viewpager
    var layoutStarted = false
    lateinit var listImages: ArrayList<ItemImageChar>
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

        //recebendo os args
        val serieInfo = args.apiObj
        when (serieInfo.id) {
            "char" -> {
                viewModel.getSeriesChar(serieInfo.id)
            }
            "comic" -> {
                tv_descricao_frag_series.setText("NOT FOUND. We don´t see this comming.")
            }
            "series" -> {
                viewModel.getSerie(serieInfo.id)
            }
            "stories" -> {
                viewModel.getSeriesStories(serieInfo.id)
            }

        }

        //-----> não entendi essa parte direito e no linha do char e do serie tá vermelho
        //configurando viewpager
        val vp = view.vp_images
        val indicator = view.ci_images
        viewModel.serie.observe(viewLifecycleOwner, {
            exibeInfo(view, it[0])
            listSeries = it
            it.forEach { linhaSerie ->
                listImages.add(
                    ItemImageChar(
                        linhaSerie.thumbnail,
                        apiObject(
                            "serie",
                            linhaSerie.id
                        )
                    )
                )

            }
            adapter.updateList(listImages)
            indicator.setViewPager(vp)
        })


        //Lucas, vc pode me explicar melhor essa coisa de flag por favor? :)
        if (!layoutStarted){
            listImages = arrayListOf()
            adapter = ExibeSerieAdapter(listImages, this)
            layoutStarted = true
        }

        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //atualizar as info
        vp.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

        })


    }


    fun exibeInfo(view: View, res: Results) {
        (activity as MainActivity).supportActionBar?.title = res.title
        view.tv_titulo_frag_series.text = res.title
        if (res.description == null || res.description == "")
            view.tv_descricao_frag_series.text = "No description found..."
        else
            view.tv_descricao_frag_series.text = res.description
    }
}