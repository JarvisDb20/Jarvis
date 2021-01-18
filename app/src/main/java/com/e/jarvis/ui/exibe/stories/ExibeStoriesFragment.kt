package com.e.jarvis.ui.exibe.stories

import androidx.navigation.fragment.findNavController
import com.e.jarvis.ui.exibe.ExibeBaseFragment

class ExibeStoriesFragment : ExibeBaseFragment() {
    override var origin = "series"

    override fun resultClick(position: Int) {
        val directions =
            ExibeStoriesFragmentDirections.actionExibeStoriesFragmentToImageFullFragment(
                getImageFull(position)
            )
        findNavController().navigate(directions)
    }

}