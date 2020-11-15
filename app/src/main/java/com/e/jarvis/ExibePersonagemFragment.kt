package com.e.jarvis

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

class ExibePersonagemFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exibe_personagem, container, false)


        return view


    }


}