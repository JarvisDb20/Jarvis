package com.e.jarvis.repository


import com.e.jarvis.dao.FavoritoDao
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.generics.GenericWrapper
import com.e.jarvis.models.modelsfavoritos.Favorito
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//aqui teremos a interface que implementa os métodos que fazem a requisição e também a instancia do service

interface Service {

    //RETORNAM CHARS:

    //pega um char especifico pelo id dele
    @GET("characters/{id}")
    suspend fun getCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"

    ): GenericWrapper

    //pega os chars do comic especifico

    @GET("comics/{id}/characters")
    suspend fun getCharComicRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega os chars da serie especifica
    @GET("series/{id}/characters")
    suspend fun getCharSeriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"

    ): GenericWrapper

    //pega os chars da storie especifica
    @GET("stories/{id}/characters")
    suspend fun getCharStoriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    @GET("comics")
    suspend fun getComicsSearch(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("titleStartsWith") startWith: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    @GET("series")
    suspend fun getSeriesSearch(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("titleStartsWith") startWith: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    @GET("characters")
    suspend fun getCharSearch(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") startWith: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper


    //RETORNAM SERIES:

    //pega serie especifica pelo id
    @GET("series/{id}")
    suspend fun getSerieRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    // não é possível pegar series a partir de um comic

    //pega series de um personagem especifico
    @GET("characters/{id}/series")
    suspend fun getSeriesCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega series de uma storie especifica
    @GET("stories/{id}/series")
    suspend fun getSeriesStoriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper


    //RETORNAM COMICS:

    //pega comic unico pelo id
    @GET("comics/{id}")
    suspend fun getComicRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de um personagem especifico
    @GET("characters/{id}/comics")
    suspend fun getComicsCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de uma storie especifica
    @GET("stories/{id}/comics")
    suspend fun getComicStorieRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de uma serie especifica
    @GET("series/{id}/comics")
    suspend fun getComicSeriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //Stories

    //pega stories de uma comic especifica
    @GET("comics/{id}/stories")
    suspend fun getStoriesComicsRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de um char especifico
    @GET("characters/{id}/stories")
    suspend fun getStoriesCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de uma serie especifica
    @GET("series/{id}/stories")
    suspend fun getStoriesSeriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de uma stories
    @GET("stories/{id}")
    suspend fun getStoriesStoriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

}


//parte do room:
class RepositoryDataBase(val resultsDao: ResultsDao, val favoritoDao: FavoritoDao) {
   //tabela results
    suspend fun getAllResults() = resultsDao.getAllResults()

    suspend fun getResults(id: String) = resultsDao.getResult(id)

    suspend fun addResults(results: GenericResults) = resultsDao.addResults(results)

    suspend fun deleteResults(results: GenericResults) = resultsDao.deleteResults(results)



    //tabela favoritos:
    suspend fun addFavorito(favorito: Favorito) =
        favoritoDao.addFavorito(favorito)

    //pega chars favoritos
    suspend fun getAllCharsFavoritos() = favoritoDao.getAllCharsFavoritos("char")

    //pega comics favoritos
    suspend fun getAllComicsFavoritos() = favoritoDao.getAllComicsFavoritos("comic")

    //pega series favoritos
    suspend fun getAllSeriesFavoritos() = favoritoDao.getAllSeriesFavoritos("serie")

   //pega stories favoritos
    suspend fun getAllStoriesFavoritos() = favoritoDao.getAllStoriesFavoritos("storie")

    //deleta favorito selecionado com o long click
    suspend fun deleteFavorito(favorito: Favorito) = favoritoDao.deleteFavorito(favorito)


}