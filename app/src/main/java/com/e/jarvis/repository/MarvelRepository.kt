package com.e.jarvis.repository

import com.e.jarvis.database.db.MarvelDb
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.retrofit.MarvelService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOn

//repo ele traz informações e toda lógica fica nele
//parte de banco e api

class MarvelRepository(private val marvelDb: MarvelDb,private  val marvelService: MarvelService) {
    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )


    fun getById(id:String, origin: String, info: String) : Flow<ResponseWrapper<ArrayList<GenericResults>>> = flow {
        var saida = ResponseWrapper<ArrayList<GenericResults>>(ResponseWrapper.Status.LOADING, null)
        emit(saida)
        // Tenta pegar do banco primeiro
        getDbById(id,origin,info).collect{ res->
            when (res.status){
                ResponseWrapper.Status.SUCCESS -> saida = ResponseWrapper(ResponseWrapper.Status.SUCCESS, arrayListOf(res.data!!))
                //caso nao encontre no banco
                ResponseWrapper.Status.ERROR ->{
                    //tenta pegar pela api, mesmo que de erro retorna
                    val resultApi = getAPIById(id,origin,info)
                    when (resultApi.status){
                        ResponseWrapper.Status.ERROR -> saida = ResponseWrapper(ResponseWrapper.Status.ERROR, null,resultApi.error)
                        // caso encontre na api adiciona no banco
                        // uma vez adicionado no banco ele ira identificar que o id existe e irá trazer
                        // pelo flow anterior
                        ResponseWrapper.Status.SUCCESS ->{
                            addDb(resultApi.data!!)
                            //caso seja informações cruzadas pega direto da api
                            if (origin != info){
                                emit(resultApi)
                            }
                        }
                    }
                }
            }
            emit (saida)
        }
    }.flowOn(Dispatchers.Default)



    private suspend fun addDb(genericResults: GenericResults){
        marvelDb.add(genericResults)
    }
    private suspend fun addDb(genericResults: ArrayList<GenericResults>){
        genericResults.forEach {
            addDb(it)
        }
    }

    private fun getDbById(id: String, origin: String, info: String): Flow<ResponseWrapper<GenericResults>> = flow {
        var saida = ResponseWrapper<GenericResults>(ResponseWrapper.Status.LOADING, null)
        emit(saida)
        if (origin == info)
            marvelDb.getById(id)
                .catch { error ->
                    saida = ResponseWrapper<GenericResults>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        error.message
                    )
                    emit(saida)
                }
                .collect { res ->
                    saida = if (res != null)
                        ResponseWrapper<GenericResults>(ResponseWrapper.Status.SUCCESS, res)
                    else
                        ResponseWrapper<GenericResults>(ResponseWrapper.Status.ERROR,null,"Id not found")
                    emit(saida)
                }
        else{
            saida = ResponseWrapper<GenericResults>(ResponseWrapper.Status.ERROR, null,"Try it in API")
            emit(saida)
        }
    }.flowOn(Dispatchers.Default)

    private suspend fun getAPIById(id: String, origin: String, info: String): ResponseWrapper<ArrayList<GenericResults>> {
        return try {
            val firstResult = when (info){
                "char" ->
                    when (origin){
                        "char" -> setApiObject(info, marvelService.getChar(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "comic" -> setApiObject(info, marvelService.getComicsChar(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "series" -> setApiObject(info, marvelService.getSeriesChar(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "stories" -> setApiObject(info, marvelService.getStoriesChar(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        else -> null
                    }
                "comic" ->
                    when (origin){
                        "char" -> setApiObject(info, marvelService.getCharComics(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "comic" -> setApiObject(info, marvelService.getComic(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "series" -> setApiObject(info, marvelService.getSeriesComics(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "stories" -> setApiObject(info, marvelService.getStorieComics(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        else -> null
                    }
                "series" ->
                    when (origin){
                        "char" -> setApiObject(info, marvelService.getCharSeries(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "comic" -> null
                        "series" -> setApiObject(info, marvelService.getSerie(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "stories" -> setApiObject(info, marvelService.getStoriesSeries(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        else -> null
                    }
                "stories" ->
                    when (origin){
                        "char" -> setApiObject(info, marvelService.getCharStories(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "comic" -> setApiObject(info, marvelService.getComicsStories(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "series" -> setApiObject(info, marvelService.getSeriesStories(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        "stories" -> setApiObject(info, marvelService.getStories(id, hash.ts, hash.publicKey, hash.getKey()).data.results)
                        else -> null
                    }
                else -> null
            }
            if (!firstResult.isNullOrEmpty())
                ResponseWrapper(ResponseWrapper.Status.SUCCESS, firstResult)
            else
                ResponseWrapper(ResponseWrapper.Status.ERROR, null,"Content not found")
        }catch (e : Exception){
            ResponseWrapper<ArrayList<GenericResults>>(ResponseWrapper.Status.ERROR, null,e.message)
        }
    }


    suspend fun getSearch(starString: String): Flow<ResponseWrapper<ArrayList<GenericResults>>> = flow {
        var saida = ResponseWrapper<ArrayList<GenericResults>>(ResponseWrapper.Status.LOADING, null)
        emit(saida)
        try {
            val result: ArrayList<GenericResults> = arrayListOf()
            val chars = setApiObject(
                "char", marvelService.getCharSearch(
                    hash.ts, hash.publicKey, hash.getKey(),
                    "%$starString%"
                ).data.results
            )

            val comics = setApiObject(
                "comic", marvelService.getComicsSearch(
                    hash.ts, hash.publicKey, hash.getKey(),
                    "%$starString%"
                ).data.results
            )

            val series = setApiObject(
                "series", marvelService.getSeriesSearch(
                    hash.ts, hash.publicKey, hash.getKey(),
                    "%$starString%"
                ).data.results
            )

            result.addAll(chars)
            result.addAll(comics)
            result.addAll(series)
            saida =
                ResponseWrapper<ArrayList<GenericResults>>(ResponseWrapper.Status.SUCCESS, result)

        } catch (e: Exception) {
            saida = ResponseWrapper<ArrayList<GenericResults>>(
                ResponseWrapper.Status.ERROR,
                null,
                e.message
            )
        }
        emit(saida)
    }.flowOn(Dispatchers.Default)

    private fun setApiObject(tipoid: String,results: ArrayList<GenericResults>): ArrayList<GenericResults> {
        results.forEach { res ->
            setApiObject(tipoid,res)
        }
        return results
    }

    private fun setApiObject(tipoid: String,result: GenericResults): GenericResults {
        result.apiObject = ApiObject(result.id, tipoid)
        return result
    }
}