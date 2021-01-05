package com.e.jarvis.ui.exibe.chars

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.e.jarvis.MainActivity
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage
import kotlinx.android.synthetic.main.fragment_exibe_char.view.*
import kotlinx.android.synthetic.main.item_exibe_botoes.view.*
import kotlinx.android.synthetic.main.item_exibe_circle_viewpager.*
import kotlinx.android.synthetic.main.item_exibe_circle_viewpager.view.*
import me.relex.circleindicator.CircleIndicator3
import org.koin.android.viewmodel.ext.android.viewModel


class ExibeCharFragment : Fragment(), ExibeCharAdapter.onClickListener {

    //posição do item na lista do adapter
     var posicao = 0

    //variavel que vai receber os args que vem do fragment home
    val args: ExibeCharFragmentArgs by navArgs()

    lateinit var listChar: ArrayList<GenericResults>


    //viriaveis para o viewpager
    var layoutStarted = false
    lateinit var listImages: ArrayList<ItemImage>
    lateinit var adapter: ExibeCharAdapter


    private val viewModel: ExibeCharViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_exibe_char, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_drawer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listImages = arrayListOf()

        val vp = view.findViewById<ViewPager2>(R.id.vp_images)

        val charInfo = args.apiObj
        when (charInfo.tipoId) {
            "char" -> {
                viewModel.getChar(charInfo.id)
                configuraProgressBar(view)
            }
            "comic" -> {
                viewModel.getCharComics(charInfo.id)
                configuraProgressBar(view)
            }
            "series" -> {
                viewModel.getCharDaSerie(charInfo.id)
                configuraProgressBar(view)
            }
            "stories" -> {
                viewModel.getCharDaStories(charInfo.id)
                configuraProgressBar(view)
            }
        }


        val indicator = view.findViewById<CircleIndicator3>(R.id.ci_images)
        viewModel.char.observe(viewLifecycleOwner, {

            if (it.size != 0) {
                exibeInfo(view, it[0])
                listChar = it as ArrayList<GenericResults>

                it.forEach { linha ->

                    viewModel.addResults(linha)

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
                view.tv_titulo_frag_char.text = "Not found..."
                view.tv_descricao_frag_char.text = "No description found..."

                view.vp_images.setBackgroundColor(Color.GRAY)
            }

        })

        // configurando o viewpager
        if (!layoutStarted) {
            adapter = ExibeCharAdapter(listImages, this)
            layoutStarted = true
        }

        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //quando trocar de pagina atualiza as informações
        vp.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    exibeInfo(view, listChar[position])
                    posicao = position
                    super.onPageSelected(position)
                }
            })


        view.btn_exibe_char.setBackgroundColor(Color.DKGRAY)

        view.btn_exibe_series.setOnClickListener {

            val passaArgsChar =
                ExibeCharFragmentDirections.navigatePersonagemToExibeSeriesFragment(args.apiObj)
            findNavController().navigate(passaArgsChar)

        }

        view.btn_exibe_comics.setOnClickListener {
            val passaArgsChar =
                ExibeCharFragmentDirections.navigatePersonagemToExibeComicsFragment(charInfo)
            findNavController().navigate(passaArgsChar)
        }

        view.btn_exibe_stories.setOnClickListener {
            val passaArgsChar =
                ExibeCharFragmentDirections.navigatePersonagemToExibeStoriesFragment(charInfo)
            findNavController().navigate(passaArgsChar)
        }
    }

    private fun configuraProgressBar(view: View) {
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it == 1) {
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            } else {
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        R.id.menu_favoritar -> {
            viewModel.addFavorito(listChar[posicao])
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun charsClick(position: Int) {
        val directions =
            ExibeCharFragmentDirections.actionExibePersonagemFragmentToImageFullFragment(
                ItemImage(
                    listImages[position].thumb,
                    args.apiObj,
                    listChar[position].name!!
                )
            )
        findNavController().navigate(directions)
    }

    fun exibeInfo(view: View, res: GenericResults) {
        (activity as MainActivity).supportActionBar?.title = res.name
        view.tv_titulo_frag_char.text = res.name
        if (res.description == null || res.description == "")
            view.tv_descricao_frag_char.text = "No description found..."
        else
            view.tv_descricao_frag_char.text = res.description
    }
}