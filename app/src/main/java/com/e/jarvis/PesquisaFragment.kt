package com.e.jarvis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_pesquisa.view.*


class PesquisaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pesquisa, container, false)

        view.barra_pesquisa2.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navigate_to_exibe_personagem_fragment)
        }

        return view
    }

}