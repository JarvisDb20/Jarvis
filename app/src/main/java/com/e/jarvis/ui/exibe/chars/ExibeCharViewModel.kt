package com.e.jarvis.ui.exibe.chars

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExibeCharViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {


    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val char = MutableLiveData<List<GenericResults>>()

    val loading = MutableLiveData<Int>()

    fun getChar(id: String) {
        loading.value = 1

        viewModelScope.launch {
            delay(1500)

            var charExibido = dataBase.getResults(id)


            if (charExibido == null) {
                charExibido =
                    service.getCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
                Log.i("EXIBECHARVIEWMODEL", "pegou da api")
               dataBase.addResults(charExibido[0])

            }


         char.value = charExibido

            loading.value = 0
        }


    }

    fun getCharComics(id: String) {
        loading.value = 1

        viewModelScope.launch {

            delay(1000)

            char.value =
                service.getCharComicRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results



            loading.value = 0
        }

    }

    fun getCharDaSerie(id: String) {

        loading.value = 1
        viewModelScope.launch {

            delay(1500)

            char.value =
                service.getCharSeriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results


            loading.value = 0
        }
    }

    fun getCharDaStories(id: String) {
        loading.value = 1
        viewModelScope.launch {

            delay(1500)

            char.value = service.getCharStoriesRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results


            loading.value = 0
        }
    }


    //room tabela results
    fun addResults(char: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(char)
        }
    }

    //room tabela favoritos
    fun addFavorito(aChar: GenericResults) {
        viewModelScope.launch {
            dataBase.addFavorito(Favorito(aChar.id, aChar, "char"))
        }
    }
}