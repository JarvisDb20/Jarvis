package com.e.jarvis.database.dao

import androidx.room.*
import com.e.jarvis.models.modelsQuiz.Quiz

@Dao
interface QuizDao {

    @Query("SELECT * FROM Quiz WHERE id= :id")
    suspend fun getQuiz(id: Int): Quiz

    @Query("SELECT id FROM Quiz ")
    suspend fun getAllIds(): List<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quiz: Quiz)


}
