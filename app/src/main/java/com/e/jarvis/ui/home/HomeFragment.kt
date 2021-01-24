package com.e.jarvis.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.MenuAppBar
import com.e.jarvis.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() , HomeAdapter.onClickListener {
    var layoutStarted = false
    lateinit var chars: ArrayList<GenericResults>
    lateinit var adapter: HomeAdapter
    lateinit var gManager: GridLayoutManager
    override var menuAppBar: MenuAppBar = MenuAppBar(false, false, true)

    private val viewModel: HomeViewModel by viewModel()
    override var searchNavigateSubmit = R.id.navigate_to_pesquisa_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pega a lista que vem da API e passa para o metodo do adapter
        viewModel.topChars.observe(viewLifecycleOwner, { res->
            when (res.status){
                ResponseWrapper.Status.ERROR -> sendMessage(res.error.toString())
                ResponseWrapper.Status.SUCCESS ->{
                    chars = ArrayList(res.data!!)
                    adapter.updateChars(ArrayList(res.data!!))
                }
            }
        })


        //lista de chars que aparecem estaticamente
        if (!layoutStarted) {
            chars = arrayListOf()
            adapter = HomeAdapter(chars, this)
        }

        viewModel.loading.observe(viewLifecycleOwner, {
            configuraProgressBar(it)

        })
        searchString.observe(viewLifecycleOwner,{
            sharedModel.setSeach(it)
        })

        //configurando a recyclerview
        val rvHome = view.findViewById<RecyclerView>(R.id.rv_home)
        gManager = GridLayoutManager(context, 3)
        rvHome.setHasFixedSize(true)
        rvHome.layoutManager = gManager
        rvHome.adapter = adapter

    }

    //clique no char, passa o id dele e o tipo para o Api Object
    override fun charsClick(position: Int) {
        sharedModel.setSelectedResult(chars[position])
        findNavController().navigate(R.id.action_nav_home_to_exibePersonagemFragment)
    }

    private fun configuraProgressBar(visible : Int) {
        if (visible == View.VISIBLE) {
            view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
        } else {
            view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.INVISIBLE
        }
    }
}