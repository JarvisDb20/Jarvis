package com.e.jarvis.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.e.jarvis.R
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favoritos_dialog.view.*

class DialogFragmentDelete(var listener: NoticeDialogListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val objFavorito = getbundle()


            val picasso = Picasso.get()
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;

            val dialogLayout = inflater.inflate(R.layout.favoritos_dialog, null)

            picasso.load(objFavorito.results?.thumbnail?.path + "/portrait_uncanny." + objFavorito.results?.thumbnail?.extension)
                .into(dialogLayout.image_dialog)
            dialogLayout.tv_dialog_favorito

            if (objFavorito.tipoDoResult == "char") {
                dialogLayout.tv_dialog_favorito.text = objFavorito.results?.name
            } else {
                dialogLayout.tv_dialog_favorito.text = objFavorito.results?.title
            }

            dialogLayout.btn_deletar_dialog.setOnClickListener {
                listener.onDialogDeleteClick(this, objFavorito)
                Log.i("CHAMOU INTERFACE", "delete ")
                dismiss()
            }

            dialogLayout.btn_ver_info_dialog.setOnClickListener {
                listener.onDialogVerInfo(this, objFavorito)
                dismiss()
            }
            builder.setView(dialogLayout)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface NoticeDialogListener {
        fun onDialogDeleteClick(dialog: DialogFragment, favorito: Favorito)
        fun onDialogVerInfo(dialog: DialogFragment, objFavorito: Favorito)
    }

    fun getbundle(): Favorito {
        val bund = arguments?.get("key") as Favorito
        return bund
    }

}