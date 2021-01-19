package com.e.jarvis.ui.pesquisa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.MenuAppBar
import com.e.jarvis.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class PesquisaFragment : BaseFragment(), PesquisaAdapter.onClickListener {

    var listSearches: ArrayList<GenericResults> = arrayListOf()
    var layoutStarted = false
    lateinit var adapter: PesquisaAdapter
    private val viewModel: PesquisaViewModel by viewModel()
    override var menuAppBar: MenuAppBar = MenuAppBar(false, false, true)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesquisa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!layoutStarted) {
            adapter = PesquisaAdapter(listSearches, this)
            layoutStarted = true
        }

        searchString.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.length > 3) {
                    viewModel.getSearch(it.toString())
                }
            }
        })

        //configurando recyclerview que exibe os resultados da chamada da api
        val rvSearch = view.findViewById<RecyclerView>(R.id.rv_search)
        rvSearch.adapter = adapter
        rvSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //passa a lista que recebeu a API para o adapter
        viewModel.listSearch.observe(viewLifecycleOwner, { res ->
            when (res.status) {
                ResponseWrapper.Status.ERROR -> sendMessage(res.error.toString())
                else -> {
                    adapter.updateList(res.data!!)
                    listSearches = res.data!!
                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            configuraProgressBar(it)
        })
    }

    override fun searchClick(position: Int, view: View) {
        val currentItem = listSearches[position]
        sharedModel.setSelectedResult(currentItem)
        findNavController().navigate(R.id.navigate_to_exibe_personagem_fragment)
        view.hideKeyboard()
    }

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun configuraProgressBar(visible: Int) {
        if (visible == View.VISIBLE) {
            view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
        } else {
            view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.INVISIBLE
        }
    }
}