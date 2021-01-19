package com.e.jarvis.ui.exibe.series

import androidx.navigation.fragment.findNavController
import com.e.jarvis.ui.exibe.ExibeBaseFragment

class ExibeSeriesFragement : ExibeBaseFragment() {
    override var origin = "series"

    override fun resultClick(position: Int) {
        val directions =
            ExibeSeriesFragementDirections.actionExibeSeriesFragementToImageFullFragment(
                getImageFull(position)
            )
        findNavController().navigate(directions)
    }


}