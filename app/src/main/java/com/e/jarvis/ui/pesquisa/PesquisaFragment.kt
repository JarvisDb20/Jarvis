package com.e.jarvis.ui.pesquisa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class PesquisaFragment : BaseFragment(), PesquisaAdapter.onClickListener {

    var listSearches: ArrayList<GenericResults> = arrayListOf()

    var layoutStarted = false
    lateinit var adapter: PesquisaAdapter

    private val viewModel: PesquisaViewModel by viewModel()

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

        //campo de entrada do searchview
        //quando o campo não está nulo e tem mais de 3 letras, faz a chamada pra API
        val svSearch = view.findViewById<EditText>(R.id.sv_search)
        svSearch.doAfterTextChanged {
            if (it != null) {
                if (it.length > 3)
                    viewModel.getSearch(it.toString())
                configuraProgressBar(view)
            }
        }

        //configurando recyclerview que exibe os resultados da chamada da api
        val rvSearch = view.findViewById<RecyclerView>(R.id.rv_search)
        rvSearch.adapter = adapter
        rvSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //passa a lista que recebeu a API para o adapter
        viewModel.listSearch.observe(viewLifecycleOwner, {
            adapter.updateList(it)
            listSearches = it
        })
    }

    override fun searchClick(position: Int, view: View) {
        //pega item da lista do adapter
        val currentItem = listSearches[position]

        //viewmodel vai retornar o apiObject, dizendo o id e qual tipo ele é

        sharedModel.setSelectedResult(currentItem)

        //findNavController().navigate(R.id.ac)

        //chamada para fechar o teclado
        view.hideKeyboard()

    }

    //função para fechar o teclado
    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
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