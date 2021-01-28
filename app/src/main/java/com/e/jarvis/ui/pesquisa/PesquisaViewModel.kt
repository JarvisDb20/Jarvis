package com.e.jarvis.ui.pesquisa

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.MarvelRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PesquisaViewModel(private val marvelRepo: MarvelRepository) : ViewModel() {

    val listSearch = MutableLiveData<ResponseWrapper<ArrayList<GenericResults>>>()
    val loading = MutableLiveData<Int>()


    fun getSearch(starString: String) {
        loading.value = View.VISIBLE
        viewModelScope.launch {
            when (listSearch.value?.status) {
                else -> {
                    marvelRepo
                        .getSearch(starString)
                        // em caso  de erro
                        .catch { error ->
                            listSearch.value = ResponseWrapper<ArrayList<GenericResults>>(
                                ResponseWrapper.Status.ERROR,
                                null,
                                error.message
                            )
                            loading.value = View.INVISIBLE
                        }
                        // assim q vier uma resposta ele cai no collect
                        .collect { res ->
                            when (res.status) {
                                ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                                else -> {
                                    listSearch.value = res
                                    loading.value = View.INVISIBLE
                                }
                            }
                        }
                }
            }
        }
    }

}