package com.e.jarvis.repository

import com.e.jarvis.dao.QuizDao
import com.e.jarvis.models.modelsQuiz.Quiz

//parte do room:
class QuizRepository(val quizDao: QuizDao) {

    suspend fun getQuiz(id : Int) = quizDao.getQuiz(id)

    suspend fun addQuiz(quiz: Quiz) = quizDao.addQuiz(quiz)

    suspend fun getAllIds() = quizDao.getAllIds()
}