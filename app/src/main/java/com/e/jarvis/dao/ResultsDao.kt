package com.e.jarvis.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.e.jarvis.models.generics.GenericResults

@Dao
interface ResultsDao {

    @Query("SELECT * FROM resultsdb")
    suspend fun getAllResults(): List<GenericResults>

    @Query ("SELECT * FROM resultsdb WHERE id= :id")
    suspend fun getResults(id: Int) : List<GenericResults>

    @Insert
    suspend fun addResults(results: GenericResults)

    @Delete
    suspend fun deleteResults(results: GenericResults)

}