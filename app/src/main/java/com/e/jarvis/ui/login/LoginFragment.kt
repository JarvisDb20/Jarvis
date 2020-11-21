package com.e.jarvis.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.e.jarvis.R
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.btn_login_newUser.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_cadastroFragment)
        }
        return view
    }

}