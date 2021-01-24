package com.e.jarvis.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.MarvelRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(val marvelRepo: MarvelRepository) : ViewModel() {

    val topChars = MutableLiveData<ResponseWrapper<HashSet<GenericResults>>>()
    val loading = MutableLiveData<Int>()

    // utilizar o analytics para pegar lista
    private val topIds = arrayListOf(
        "1009220", // capitao america
        "1010338", // capita marvel
        "1010743", // groot
        "1010744", // rocket racoon
        "1009496", // jean grey
        "1009718", // wolverine
        "1010860", // garota esquilo
        "1009268", // deadpool
        "1009610"  // homem aranha
    )

    init {
        getChars()
    }

    fun getChars() {
        val topLista = HashSet<GenericResults>()

        viewModelScope.launch {
            topIds.forEach {
                when (topChars.value?.status) {
                    ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                    else -> {
                        marvelRepo.getById(it,"char","char")
                            .catch { error ->
                                topChars.value = ResponseWrapper<HashSet<GenericResults>>(
                                    ResponseWrapper.Status.ERROR,
                                    null,
                                    error.message
                                )
                                loading.value = View.INVISIBLE
                            }
                            .onEach { res ->
                                when (res.status) {
                                    ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                                    else -> {
                                        topLista.addAll(res.data!!)
                                        topChars.value = ResponseWrapper(
                                            ResponseWrapper.Status.SUCCESS,
                                            topLista
                                        )
                                    }
                                }
                                loading.value = View.INVISIBLE
                            }
                            .launchIn(this)
                    }
                }
            }
        }
    }
}

