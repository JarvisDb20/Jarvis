package com.e.jarvis.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.ItemImage

import com.e.jarvis.repository.service


class PesquisaFragment : Fragment(), PesquisaAdapter.onClickListener {
    var listSearches:ArrayList<GenericResults> = arrayListOf()

    //viriaveis para o viewpager
    var layoutStarted = false
    lateinit var listImages : ArrayList<ItemImage>
    lateinit var adapter: PesquisaAdapter


    private val viewModel by viewModels<PesquisaViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PesquisaViewModel(service) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesquisa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvSearch = view.findViewById<RecyclerView>(R.id.rv_search)

        
        if (!layoutStarted) {
            adapter = PesquisaAdapter(listSearches, this)
            layoutStarted = true
        }
        rvSearch.adapter = adapter
        rvSearch.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val svSearch = view.findViewById<EditText>(R.id.sv_search)

        viewModel.listSearch.observe(viewLifecycleOwner, {
            adapter.updateList(it)
            listSearches = it

        })
        svSearch.doAfterTextChanged{
            if (it != null) {
                if (it.length > 3)
                    viewModel.getSearch(it.toString())
            }
        }
//        view.sv_search.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.navigate_to_exibe_personagem_fragment)
//        }
    }

    override fun searchClick(position: Int) {
        val currentItem = listSearches[position]
        var apiObject : ApiObject = ApiObject("","")
        if (currentItem.series == null)
            apiObject = ApiObject(currentItem.id,"series")
        else if (currentItem.comics == null)
            apiObject = ApiObject(currentItem.id,"comic")
        else if (currentItem.characters == null)
            apiObject = ApiObject(currentItem.id,"char")

        val direction = PesquisaFragmentDirections.navigateToExibePersonagemFragment(apiObject)
        findNavController().navigate(direction)

    }
}