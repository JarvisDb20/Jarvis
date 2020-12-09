package com.e.jarvis.ui.exibe.story

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.e.jarvis.MainActivity
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage
import com.e.jarvis.repository.service
import com.e.jarvis.ui.exibe.comic.ExibeComicsFragmentDirections
import kotlinx.android.synthetic.main.fragment_exibe_stories.view.*
import kotlinx.android.synthetic.main.item_exibe.view.*
import me.relex.circleindicator.CircleIndicator3

class ExibeStoriesFragment : Fragment(), ExibeStoriesAdapter.onClickListener {
    val args: ExibeStoriesFragmentArgs by navArgs()
    lateinit var listStories:ArrayList<GenericResults>
    var layoutStarted = false
    var listImages : ArrayList<ItemImage> = arrayListOf()
    lateinit var adapter: ExibeStoriesAdapter


    private val viewModel by viewModels<ExibeStoriesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ExibeStoriesViewModel(service) as T
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_exibe_stories, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storiesInfo = args.apiObj
        val vp = view.findViewById<ViewPager2>(R.id.vp_images)


        when (storiesInfo.tipoId) {
            "comics" -> {
                viewModel.getStoriesComics(storiesInfo.id)
            }
            "char" ->{
                viewModel.getStoriesChar(storiesInfo.id)
            }
            "series" ->{
                viewModel.getStoriesSeries(storiesInfo.id)
            }
            "stories" ->{
                viewModel.getStoriesStories(storiesInfo.id)
            }
        }

        val indicator = view.findViewById<CircleIndicator3>(R.id.ci_images)
        viewModel.stories.observe(viewLifecycleOwner, {
            exibeInfo(view,it[0])
            listStories = it
            it.forEach {
                linha ->
                listImages.add(
                        ItemImage(
                                linha.thumbnail,
                                ApiObject(
                                        "stories",
                                        linha.id
                                )
                        )
                )
            }
            adapter.updateList(listImages)
            indicator.setViewPager(vp)
        })
        // configurando o viewpager
        if (!layoutStarted) {
            listImages = arrayListOf()
            adapter = ExibeStoriesAdapter(listImages, this)
            layoutStarted = true
        }
        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //quando trocar de pagina atualiza as informações
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                exibeInfo(view,listStories[position])
                super.onPageSelected(position)
            }
        })

        view.btn_exibe_stories.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_char.setOnClickListener {
            val passaArgsComic = ExibeComicsFragmentDirections.navigateComicsToPersonagem(args.apiObj)
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
        }

    override fun storiesClick(position: Int) {
        Log.i("storiesClick", listImages[position].toString())
    }
    fun exibeInfo(view : View,res :GenericResults){
        (activity as MainActivity).supportActionBar?.title = res.name
        view.tv_titulo_frag_stories.text = res.name
        if (res.description == null || res.description == "" )
            view.tv_descricao_frag_stories.text = "No description found..."
        else
            view.tv_descricao_frag_stories.text = res.description
    }
}