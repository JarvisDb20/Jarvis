package com.e.jarvis.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import kotlinx.android.synthetic.main.fragment_quiz.view.*

class QuizFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_quiz, container, false)
        
        return view

    }
    companion object{
        fun newInstance() = QuizFragment()
    }
}