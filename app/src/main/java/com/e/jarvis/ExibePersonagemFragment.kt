package com.e.jarvis

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_exibe_personagem.view.*

class ExibePersonagemFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_personagem, container, false)

        view.frag_personagem_btn_char.setOnClickListener() {
            findNavController().navigate(R.id.action_exibePersonagemFragment2_to_exibeSeriesFragement)
        }



        return view


    }


}