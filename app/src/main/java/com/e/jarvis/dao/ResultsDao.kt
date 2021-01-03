package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.generics.GenericResults

@Dao
interface ResultsDao {

    @Query("SELECT * FROM results")
    suspend fun getAllResults(): List<GenericResults>

    @Query ("SELECT * FROM results WHERE id= :id")
    suspend fun getResults(id: Int) : List<GenericResults>

    @Insert
    suspend fun addResults(results: GenericResults)

    @Delete
    suspend fun deleteResults(results: GenericResults)

}