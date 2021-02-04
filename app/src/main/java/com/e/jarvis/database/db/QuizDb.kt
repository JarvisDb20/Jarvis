package com.e.jarvis.database.db

import com.e.jarvis.database.dao.QuizDao
import com.e.jarvis.models.modelsQuiz.Quiz

class QuizDb(val quizDao: QuizDao) {

    suspend fun getQuiz(id : Int) = quizDao.getQuiz(id)

    suspend fun addQuiz(quiz: Quiz) = quizDao.addQuiz(quiz)

    suspend fun getAllIds() = quizDao.getAllIds()

    suspend fun delAllQuiz() = quizDao.delAllQuiz()

}