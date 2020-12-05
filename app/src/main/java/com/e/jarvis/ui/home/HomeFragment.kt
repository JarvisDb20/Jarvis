package com.e.jarvis.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.jarvis.R
import com.e.jarvis.models.utils.apiObject
import com.e.jarvis.models.chars.Results
import com.e.jarvis.repository.service
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() , HomeAdapter.onClickListener {
    var layoutStarted = false
    lateinit var chars: ArrayList<Results>
    lateinit var adapter: HomeAdapter
    lateinit var gManager: GridLayoutManager
    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(service) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.sv_home.setOnClickListener {
            Navigation.findNavController(view)
                    .navigate(R.id.navigate_to_pesquisa_fragment)
        }

        var rvHome = view.findViewById<RecyclerView>(R.id.rv_home)

        viewModel.chars.observe(viewLifecycleOwner, {
            chars = it
            adapter.updateChars(it)
            Log.i("Inicio", it.toString())
        })


        gManager = GridLayoutManager(context, 3)
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
        }
        rvHome.setHasFixedSize(true)
        rvHome.layoutManager = gManager
        rvHome.adapter = adapter

    }

    override fun charsClick(position: Int) {
        //65123
        val api : apiObject

        if (chars[position].id == "1010744"){
            // simulando pegar uma comic quando clicar no rocket
            api = apiObject(
                "65123",
                "comic"
            )
        }else {
            api = apiObject(
                chars[position].id,
                "char"
            )
        }
        val direction = HomeFragmentDirections.actionNavHomeToExibePersonagemFragment(api)
        findNavController().navigate(direction)
    }
}