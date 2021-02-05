package com.e.jarvis.ui.logout

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import com.e.jarvis.ui.exibe.ExibeAdapter
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class LogoutFragment : BaseFragment() {
    private val vm: LogoutViewModel by viewModel()
    private val logout : Task<Void> by inject()

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
        findNavController().popBackStack()
        finishAffinity(this.requireActivity())

    }

}