package com.e.jarvis.ui.exibe.chars

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
import com.e.jarvis.models.chars.Results
import com.e.jarvis.models.utils.ItemImageChar
import com.e.jarvis.models.utils.apiObject
import com.e.jarvis.repository.service
import kotlinx.android.synthetic.main.fragment_exibe_char.view.*
import kotlinx.android.synthetic.main.item_exibe.view.*
import me.relex.circleindicator.CircleIndicator3


class ExibeCharFragment : Fragment(), ExibeCharAdapter.onClickListener {

    //variavel que vai receber os args
    val args: ExibeCharFragmentArgs by navArgs()
    lateinit var listChar: ArrayList<Results>

    //viriaveis para o viewpager
    var layoutStarted = false
    lateinit var listImages: ArrayList<ItemImageChar>
    lateinit var adapter: ExibeCharAdapter



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

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //args que o fragment char tá recebendo
        val charInfo = args.apiObj

        val vp = view.findViewById<ViewPager2>(R.id.vp_images)


        when (charInfo.tipoId) {
            "char" -> {
                viewModel.getChar(charInfo.id)
            }
            "comic" -> {
                viewModel.getCharComics(charInfo.id)
            }
            "series" -> {
                viewModel.getCharDaSerie(charInfo.id)
            }
            "stories" -> {
                viewModel.getCharDaStories(charInfo.id)
            }

        }
        val indicator = view.findViewById<CircleIndicator3>(R.id.ci_images)
        viewModel.char.observe(viewLifecycleOwner, {
            exibeInfo(view, it[0])
            listChar = it
            it.forEach { linha ->
                listImages.add(
                    ItemImageChar(
                        linha.thumbnail,
                        apiObject(
                            "char",
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
            adapter = ExibeCharAdapter(listImages, this)
            layoutStarted = true
        }
        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //quando trocar de pagina atualiza as informações
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                exibeInfo(view, listChar[position])
                super.onPageSelected(position)
            }
        })


        view.btn_exibe_char.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_series.setOnClickListener {

            val passaArgsChar = ExibeCharFragmentDirections.navigatePersonagemToExibeSeriesFragment( args.apiObj)
            findNavController().navigate(passaArgsChar)

        }

        view.btn_exibe_comics.setOnClickListener {
            val passaArgsChar = ExibeCharFragmentDirections.navigatePersonagemToExibeComicsFragment( args.apiObj)
            findNavController().navigate(passaArgsChar)
        }

        view.btn_exibe_stories.setOnClickListener {
//            val passaArgsChar = ExibeCharFragmentDirections.navigatePersonagemToExibeStoriesFragment( args.apiObj)
//            findNavController().navigate(passaArgsChar)
        }
    }

    override fun charsClick(position: Int) {
        Log.i("click", listImages[position].toString())
    }

    fun exibeInfo(view: View, res: Results) {
        (activity as MainActivity).supportActionBar?.title = res.name
        view.tv_titulo_frag_char.text = res.name
        if (res.description == null || res.description == "")
            view.tv_descricao_frag_char.text = "No description found..."
        else
            view.tv_descricao_frag_char.text = res.description
    }
}