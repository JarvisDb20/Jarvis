package com.e.jarvis.ui.cadastro

import android.os.Bundle
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.UserModel
import com.e.jarvis.ui.BaseFragment
import com.e.jarvis.ui.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_cadastro.view.*
import org.koin.android.viewmodel.ext.android.viewModel
=======
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051


class CadastroFragment : BaseFragment() {

<<<<<<< HEAD
    val vm: CadastroViewModel by viewModel()
=======
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

<<<<<<< HEAD
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_cadastro_register.setOnClickListener {
            val user = UserModel(view.et_cadastro_email.text.toString(),view.et_cadastro_password.text.toString())
            vm.addUser(user).observe(viewLifecycleOwner,{
                when (it.status) {
                    ResponseWrapper.Status.ERROR -> sendMessage(it.error!!)
                    ResponseWrapper.Status.SUCCESS -> {
                        findNavController().popBackStack()
                    }
                }
            })
        }

    }
=======
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
}