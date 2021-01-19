package com.e.jarvis.ui.exibe.comic

import androidx.navigation.fragment.findNavController
import com.e.jarvis.ui.exibe.ExibeBaseFragment

class ExibeComicsFragment : ExibeBaseFragment() {
    override var origin = "comic"

    override fun resultClick(position: Int) {
        val directions =
            ExibeComicsFragmentDirections.actionExibeComicsFragmentToImageFullFragment(
                getImageFull(position)
            )
        findNavController().navigate(directions)
    }

}