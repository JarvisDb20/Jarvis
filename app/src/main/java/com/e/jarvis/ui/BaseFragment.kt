package com.e.jarvis.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.e.jarvis.R
import com.e.jarvis.models.utils.DrawerMenuItem
import com.e.jarvis.models.utils.MenuAppBar
import org.koin.android.viewmodel.ext.android.sharedViewModel


abstract class BaseFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    protected open var bottomNavigationViewVisibility = View.INVISIBLE
    protected open var toolbarMenu = View.VISIBLE
    protected open var menuItemVisibility =
        DrawerMenuItem(home = true, quiz = true, favorites = true, login = true, logout = true)
    open val sharedModel: SharedViewModel by sharedViewModel()
    protected open var menuAppBar = MenuAppBar(share = false, favorite = false, search = false)
    open val searchString = MutableLiveData<String>()
    open var searchNavigateSubmit: Int = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // get the reference of the parent activity and call the setBottomNavigationVisibility method.
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            setHasOptionsMenu(true)
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toolbarMenu)
            mainActivity.setNavDrawerVisibility(menuItemVisibility)
            mainActivity.resizeFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            defaultLayout()
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toolbarMenu)
            mainActivity.setNavDrawerVisibility(menuItemVisibility)
            mainActivity.resizeFragment()
        }
    }

    private fun defaultLayout() {
        mainActivity.setBottomNavigationVisibility(View.INVISIBLE)
        mainActivity.setToolbarVisibility(View.VISIBLE)
        mainActivity.setNavDrawerVisibility(
            DrawerMenuItem(
                home = true,
                quiz = true,
                favorites = true,
                login = true,
                logout = true
            )
        )
        mainActivity.resizeFragment()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.forEach { item ->
            when (item.title.toString().toLowerCase()) {
                "share" -> item.isVisible = menuAppBar.share
                "favorite" -> item.isVisible = menuAppBar.favorite
                "search" -> item.isVisible = menuAppBar.search
            }
        }
        val menuItem = menu.findItem(R.id.sv_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search here!"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchString.value = query
                if(searchNavigateSubmit >0)
                    findNavController().navigate(searchNavigateSubmit)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchString.value = newText
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)

    }

    protected fun sendMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}