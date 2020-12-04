package com.e.jarvis.ui.exibe.comic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.comics.Results
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import com.e.jarvis.repository.service
import kotlinx.coroutines.launch

class ExibeComicsViewModel(service: Service) : ViewModel() {


//    val hash = KeyHash( "bacf6559c29f05132ea07020962d41a65dcd3304",
//        "f28a07f38dc7090aa24b3e50496e6ac6")
//
//    //com o id do char faz requisição de: series, comics, stories
//    //para testes vou usar o id do Hulk: 1009351
//
//    //comics
//
//    val listComicsChar = MutableLiveData<List<Results>> ()
//
//    fun getComicsChar (id: String) {
//        viewModelScope.launch {
//            listComicsChar.value = service.getComicsRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
//            Log.i("EXIBECHARVIEWMODEL", listComicsChar.toString())
//        }
//
//    }
//


}