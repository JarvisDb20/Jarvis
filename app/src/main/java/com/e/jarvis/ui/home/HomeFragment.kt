package com.e.jarvis.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.e.jarvis.R
import com.e.jarvis.repository.service
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_pesquisa.view.*

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

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

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel.getAllChars("1011334")
        viewModel.todosPersonagens.observe(viewLifecycleOwner, {
            Log.i("MainActivity", it.toString())
        })


        //abre PesquisaFragment
        view.sv_home.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_pesquisa_fragment)
        }


        return view
    }
}