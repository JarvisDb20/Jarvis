package com.e.jarvis.ui.exibe


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isEmpty
import androidx.viewpager2.widget.ViewPager2
import com.e.jarvis.R
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage
import com.e.jarvis.models.utils.MenuAppBar
import com.e.jarvis.ui.BaseFragment
import com.e.jarvis.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_exibe.view.*
import me.relex.circleindicator.CircleIndicator3
import org.koin.android.viewmodel.ext.android.viewModel


//tipo um resumo pros outros frag usarem


abstract class ExibeBaseFragment : BaseFragment(), ExibeAdapter.onClickListener {

    override var bottomNavigationViewVisibility = View.VISIBLE
    override var toolbarMenu = View.VISIBLE
    override var menuAppBar: MenuAppBar = MenuAppBar(share = true, favorite = true, search = false)

    private lateinit var apiObject: ApiObject
    private var posicao = 0
    private var listResults: HashSet<GenericResults> = hashSetOf(GenericResults(""))
    private var layoutStarted = false
    private var listImages: ArrayList<ItemImage> =
        arrayListOf(ItemImage(GenericImage("", ""), ApiObject("", ""), "invalid"))
    private lateinit var adapter: ExibeAdapter
    protected open var info: String = ""
    private lateinit var indicator: CircleIndicator3
    private lateinit var vp: ViewPager2
    private val viewModel: ExibeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exibe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listImages = arrayListOf()

        apiObject = sharedModel.getSelectedResult().apiObject!!

        if (!layoutStarted) {
            adapter = ExibeAdapter(this)
            layoutStarted = true
        }

        vp = view.findViewById<ViewPager2>(R.id.vp_images)
        indicator = view.findViewById<CircleIndicator3>(R.id.ci_images)


        viewModel.getResult(sharedModel.getSelectedResult(), info)

        viewModel.result.observe(viewLifecycleOwner, { res ->
            when (res.status) {
                ResponseWrapper.Status.ERROR -> {
                    sendMessage(res.error.toString())
                    (activity as MainActivity).supportActionBar?.title = "Not found..."
                    view.tv_exibe_titulo.text = "Not found..."
                    view.tv_exibe_descricao.text = "No description found..."
                }
                ResponseWrapper.Status.SUCCESS -> {
                    exibeInfo(view, res.data!!.first(), info)
                    listResults = res.data
                    res.data!!.forEach { linha ->
                        geraListaImagem(linha)
                    }
                    adapter.updateList(listImages)
                    if (indicator.isEmpty())
                        indicator.setViewPager(vp)
                }
                else -> Log.i("teste", "onViewCreated: sei la")
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            configProgressBar(view, it)
        })


        vp.adapter = adapter
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vp.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    exibeInfo(view, listResults.elementAt(position), info)
                    posicao = position
                    super.onPageSelected(position)
                }
            })

    }

    open fun exibeInfo(view: View, res: GenericResults, info: String) {

        if (info == "char") {
            view.tv_exibe_titulo.text = res.name
            (activity as MainActivity).supportActionBar?.title = res.name
        } else {
            view.tv_exibe_titulo.text = res.title
            (activity as MainActivity).supportActionBar?.title = res.title
        }

        if (res.description == null || res.description == "")
            view.tv_exibe_descricao.text = "No description found..."
        else
            view.tv_exibe_descricao.text = res.description
    }


    open fun configProgressBar(view: View, ativo: Int) {
        if (ativo == 1) {
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        } else {
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
        }
    }

    open fun geraListaImagem(linha: GenericResults) {
        if (linha.thumbnail != null) {
            listImages.add(
                ItemImage(
                    linha.thumbnail,
                    ApiObject(
                        info,
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_favoritar -> {
            viewModel.addFavorito(listResults.elementAt(posicao))
            true
        }
        R.id.menu_share -> {
            viewModel.addFavorito(listResults.elementAt(posicao))
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, listResults.elementAt(posicao).description)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    open fun getImageFull(position: Int) = ItemImage(
        listImages[position].thumb,
        apiObject,
        listResults.elementAt(position).name ?: listResults.elementAt(position).title!!
    )

}