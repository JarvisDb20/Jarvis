package com.e.jarvis.ui.exibe

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
<<<<<<< HEAD
=======
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.repository.FavoritesRepository
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
import com.e.jarvis.repository.MarvelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

class ExibeViewModel(
<<<<<<< HEAD
    private val marvelRepo: MarvelRepository
=======
    private val marvelRepo: MarvelRepository,
    private val repoFavoritos : FavoritesRepository
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
) : ViewModel() {

    val result = MutableLiveData<ResponseWrapper<HashSet<GenericResults>>>()
    val loading = MutableLiveData<Int>()

    fun getResult(genericResults: GenericResults, info: String) {
        viewModelScope.launch {
            genericResults.apiObject?.let {
<<<<<<< HEAD
                marvelRepo.getById(genericResults.id, it.tipoId, info).collect {  res ->
=======
                marvelRepo.getById(genericResults.id, it.tipoId, info)
                    .collect {  res ->
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
                    when (res.status){
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        ResponseWrapper.Status.ERROR ->  result.value = ResponseWrapper( res.status,null,res.error)
                        else ->{
                            result.value = ResponseWrapper(res.status, res.data!!.toHashSet(),res.error)
                        }
                    }
                    loading.value = View.INVISIBLE
                }
            }
        }
    }
    //converte para hashset
    fun <T> ArrayList<out T>.toHashSet(): HashSet<T> {
        val hash = hashSetOf<T>()
        this.forEach { l ->
            hash.add(l)
        }
        return hash
    }

<<<<<<< HEAD



    fun addFavorito(result: GenericResults) {
        viewModelScope.launch {

=======
    //room tabela favoritos
    fun addFavorito(result: GenericResults) {
        viewModelScope.launch {
            result.apiObject?.let { Favorito(result.id, result, it.tipoId) }?.let { repoFavoritos.addFavorito(it) }
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
        }
    }
}