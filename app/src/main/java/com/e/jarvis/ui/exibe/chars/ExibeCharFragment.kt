package com.e.jarvis.ui.exibe.chars

import androidx.navigation.fragment.findNavController
import com.e.jarvis.ui.exibe.ExibeBaseFragment

class ExibeCharFragment : ExibeBaseFragment() {
    override var info = "char"

    override fun resultClick(position: Int) {
        val directions =
            ExibeCharFragmentDirections.actionExibePersonagemFragmentToImageFullFragment(
                getImageFull(position)
            )
        findNavController().navigate(directions)
    }
}