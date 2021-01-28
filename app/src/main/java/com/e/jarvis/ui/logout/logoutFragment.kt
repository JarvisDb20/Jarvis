package com.e.jarvis.ui.logout

import android.os.Bundle
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class logoutFragment : BaseFragment() {
    private val vm: LogoutViewModel by viewModel()
=======
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment


class logoutFragment : BaseFragment() {

>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }

<<<<<<< HEAD
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.logout()
        val directions = logoutFragmentDirections.actionLogoutFragmentToLoginFragment()
        findNavController().navigate(directions)
    }
=======
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051

}