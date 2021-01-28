package com.e.jarvis.ui.logout

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class logoutFragment : BaseFragment() {
    private val vm: LogoutViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.logout()
        val directions = logoutFragmentDirections.actionLogoutFragmentToLoginFragment()
        findNavController().navigate(directions)
    }


}