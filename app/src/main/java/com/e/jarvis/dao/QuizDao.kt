package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.models.modelsfavoritos.Favorito

@Dao
interface QuizDao {

    @Query("SELECT * FROM Quiz WHERE id= :id")
    suspend fun getQuiz(id: Int): Quiz

    @Query("SELECT id FROM Quiz ")
    suspend fun getAllIds(): List<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quiz: Quiz)


}