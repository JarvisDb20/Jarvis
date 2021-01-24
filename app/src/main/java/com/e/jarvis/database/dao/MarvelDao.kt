package com.e.jarvis.database.dao

import androidx.room.*
import com.e.jarvis.models.generics.GenericResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface MarvelDao {

    @Query("SELECT * FROM results")
    fun getAll(): Flow<List<GenericResults>>

    @Query ("SELECT * FROM results WHERE id= :id")
    fun getById(id: String) : Flow<GenericResults>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(results: GenericResults)

    @Delete
    suspend fun delete(results: GenericResults)

    fun getByIdDistinct(id: String) = getById(id).distinctUntilChanged()

}
