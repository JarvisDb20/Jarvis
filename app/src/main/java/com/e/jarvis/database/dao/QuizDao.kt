package com.e.jarvis.database.dao

import androidx.room.*
import com.e.jarvis.models.modelsQuiz.Quiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface QuizDao {

    @Query("SELECT * FROM Quiz WHERE id= :id")
    suspend fun getQuiz(id: Int): Quiz

    @Query("SELECT id FROM Quiz ")
    suspend fun getAllIds(): List<Int>

    @Query("DELETE FROM Quiz ")
    suspend fun delAllQuiz()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quiz: Quiz)



}
