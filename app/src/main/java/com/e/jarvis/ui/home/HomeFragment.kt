package com.e.jarvis.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() , HomeAdapter.onClickListener {
    var layoutStarted = false
    lateinit var chars: ArrayList<GenericResults>
    lateinit var adapter: HomeAdapter
    lateinit var gManager: GridLayoutManager

    private val viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //clique na barra de search abre frag pesquisa
        view.sv_home.setOnClickListener {
            Navigation.findNavController(view)
                    .navigate(R.id.navigate_to_pesquisa_fragment)
        }


        //pega a lista que vem da API e passa para o metodo do adapter
        viewModel.chars.observe(viewLifecycleOwner, {
            chars = it
            adapter.updateChars(it)
            Log.i("Inicio", it.toString())
        })


        //lista de chars que aparecem estaticamente
        if (!layoutStarted) {
            chars = arrayListOf()
            adapter = HomeAdapter(chars, this)
            layoutStarted = true
            viewModel.getChars(arrayListOf(
                    "1009220", // capitao america
                    "1010338", // capita marvel
                    "1010743", // groot
                    "1010744", // rocket racoon
                    "1009496", // jean grey
                    "1009718", // wolverine
                    "1010860", // garota esquilo
                    "1009268", // deadpool
                    "1009610"  // homem aranha
            ))
            configuraProgressBar(view)
        }


        //configurando a recyclerview
        val rvHome = view.findViewById<RecyclerView>(R.id.rv_home)
        gManager = GridLayoutManager(context, 3)
        rvHome.setHasFixedSize(true)
        rvHome.layoutManager = gManager
        rvHome.adapter = adapter


    }

    //clique no char, passa o id dele e o tipo para o Api Object
    override fun charsClick(position: Int) {

        val apisSimulation : ApiObject

        if (chars[position].id == "1010744"){
            // simulando pegar uma comic quando clicar no rocket
            apisSimulation = ApiObject(
                "65123",
                "comic"
            )
        }else {
            apisSimulation = ApiObject(
                chars[position].id,
                "char"
            )
        }
        sharedModel.setSelectedResult(chars[position])
        //quando clica em um item, vai para o fragment exibe personagem e passa o ApiObject
        //vai ser sempre para o frag exibe char pois aqui só vai ter top 9 char

        findNavController().navigate(R.id.action_nav_home_to_exibePersonagemFragment)
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
}