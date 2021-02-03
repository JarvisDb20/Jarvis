package com.e.jarvis.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.UserModel
import com.e.jarvis.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val vm : LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_login_newUser.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_cadastroFragment)
        }
        view.btn_login.setOnClickListener {
            val user = UserModel(view.et_login_email.text.toString(), view.et_login_password.text.toString())
            vm.login(user).observe(viewLifecycleOwner, {
                when (it.status) {
                    ResponseWrapper.Status.ERROR -> sendMessage(it.error!!)
                    ResponseWrapper.Status.SUCCESS -> {
                      //  val directions = LoginFragmentDirections.actionGlobalMainFragment()
                     //   findNavController().navigate(directions)
                    }

                }
            })
        }


    }

}