package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.generics.GenericResults

@Dao
interface ResultsDao {

    @Query("SELECT * FROM results")
    suspend fun getAllResults(): List<GenericResults>

    @Query ("SELECT * FROM results WHERE id= :id")
    suspend fun getResult(id: String) : List<GenericResults>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResults(results: GenericResults)

    @Delete
    suspend fun deleteResults(results: GenericResults)

}