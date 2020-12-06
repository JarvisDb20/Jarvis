package com.e.jarvis.repository


import com.e.jarvis.models.chars.CharWrapper
import com.e.jarvis.models.series.SeriesWrapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//aqui teremos a interface que implementa os métodos que fazem a requisição e também a instancia do service

interface Service {

    //RETORNAM CHARS

    //pega um char especifico pelo id dele
    @GET("characters/{id}")
    suspend fun getCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharWrapper

    //pega os chars do comic especifico
    @GET("comics/{id}/characters")
    suspend fun getComicsCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharWrapper

    //pega os chars da serie especifica
    @GET("series/{id}/characters")
    suspend fun getCharDaSeriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharWrapper

    //pega os chars da storie especifica
    @GET("stories/{id}/characters")
    suspend fun getCharDaStoriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharWrapper


    //RETORNAM SERIES

    //pega serie especifica pelo id
    @GET("series/{id}")
    suspend fun getSerieRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): SeriesWrapper

    // não é possível pegar series a partir de um comic

    //pega series de um personagem especifico
    @GET("characters/{id}/series")
    suspend fun getSeriesCharRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): SeriesWrapper

    //pega series de uma storie especifica
    @GET("stories/{id}/series")
    suspend fun getSeriesStoriesRepo(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): SeriesWrapper


















//    //pega os comics do char
//    @GET("characters/{id}/comics")
//    suspend fun getComicsRepo(
//        @Path("id") id: String,
//        @Query("ts") ts: String,
//        @Query("apikey") apikey: String,
//        @Query("hash") hash: String
//    ): ComicsWrapper


}

val retrofit = Retrofit.Builder().baseUrl("http://gateway.marvel.com/v1/public/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val service: Service = retrofit.create(Service::class.java)


//@GET("characters?ts=1&apikey=f28a07f38dc7090aa24b3e50496e6ac6&hash=f7aece34f231420e8d8fb2e698fa4113")