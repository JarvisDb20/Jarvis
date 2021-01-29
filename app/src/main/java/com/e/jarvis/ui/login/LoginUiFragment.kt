package com.e.jarvis.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.inject


class LoginUiFragment : BaseFragment() {

    private val AUTHCODE = 2222
    private val firebaseAuth: FirebaseAuth by inject()
    private lateinit var providers: List<AuthUI.IdpConfig>
    private lateinit var listener: FirebaseAuth.AuthStateListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_ui, container, false)
    }


    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener (listener)
    }

    override fun onStop() {
        firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLogin()

    }

    private fun initLogin() {
        providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.GitHubBuilder().build(),
                AuthUI.IdpConfig.TwitterBuilder().build(),
//                AuthUI.IdpConfig.FacebookBuilder().build(),
        )
        listener = FirebaseAuth.AuthStateListener { p0 ->
            if (p0.currentUser != null){
                findNavController().popBackStack()

            }else{
                startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setTheme(R.style.LoginTheme)
                    .setIsSmartLockEnabled(false,true)
                    .build(),AUTHCODE
                )
            }
        }
    }
}