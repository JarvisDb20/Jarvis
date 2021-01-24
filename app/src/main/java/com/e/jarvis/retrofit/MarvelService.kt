package com.e.jarvis.retrofit


import com.e.jarvis.models.generics.GenericWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//aqui teremos a interface que implementa os métodos que fazem a requisição e também a instancia do service

interface MarvelService {

    //RETORNAM CHARS:

    //pega um char especifico pelo id dele
    @GET("characters/{id}")
    suspend fun getChar(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"

    ): GenericWrapper

    //pega os chars do comic especifico

    @GET("comics/{id}/characters")
    suspend fun getComicsChar(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega os chars da serie especifica
    @GET("series/{id}/characters")
    suspend fun getSeriesChar(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"

    ): GenericWrapper

    //pega os chars da storie especifica
    @GET("stories/{id}/characters")
    suspend fun getStoriesChar(
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
    suspend fun getSerie(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    // não é possível pegar series a partir de um comic

    //pega series de um personagem especifico
    @GET("characters/{id}/series")
    suspend fun getCharSeries(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega series de uma storie especifica
    @GET("stories/{id}/series")
    suspend fun getStoriesSeries(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper


    //RETORNAM COMICS:

    //pega comic unico pelo id
    @GET("comics/{id}")
    suspend fun getComic(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de um personagem especifico
    @GET("characters/{id}/comics")
    suspend fun getCharComics(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de uma storie especifica
    @GET("stories/{id}/comics")
    suspend fun getStorieComics(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //pega comics de uma serie especifica
    @GET("series/{id}/comics")
    suspend fun getSeriesComics(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-onsaleDate"
    ): GenericWrapper

    //Stories

    //pega stories de uma comic especifica
    @GET("comics/{id}/stories")
    suspend fun getComicsStories(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de um char especifico
    @GET("characters/{id}/stories")
    suspend fun getCharStories(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de uma serie especifica
    @GET("series/{id}/stories")
    suspend fun getSeriesStories(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

    //pega stories de uma stories
    @GET("stories/{id}")
    suspend fun getStories(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "-modified"
    ): GenericWrapper

}
