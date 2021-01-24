package com.e.jarvis.database.dao

import androidx.room.*
import com.e.jarvis.models.modelsQuiz.Quiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface QuizDao {

    @Query("SELECT * FROM Quiz WHERE id= :id")
    fun getQuiz(id: Int): Flow<Quiz>

    @Query("SELECT id FROM Quiz ")
    fun getAllIds(): Flow<List<Int>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quiz: Quiz)

    fun getQuizDistinct(id: Int) = getQuiz(id).distinctUntilChanged()

}
