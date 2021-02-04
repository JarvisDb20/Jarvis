package com.e.jarvis.repository

import android.util.Log
import com.e.jarvis.database.db.QuizDb
import com.e.jarvis.models.ResponseHandler
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.models.modelsQuiz.UserQuiz
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class QuizRepository(
    private val quizDb: QuizDb,
    private val firebaseRepository: FirebaseRepository,
    private val res: ResponseHandler
) {
    fun addQuiz(quiz: Quiz, firestore: Boolean = true) = flow<ResponseWrapper<Boolean>> {
        quizDb.addQuiz(quiz)
        if (firestore)
            firebaseRepository.addQuiz(quiz).collect()
    }

    fun updateUserQuiz(userQuiz: UserQuiz) = flow<ResponseWrapper<Boolean>> {
        firebaseRepository.updateUserQuiz(userQuiz).collect()
    }

    fun getQuiz() = flow<ResponseWrapper<Quiz>> {
        getAllQuiz()
            .catch { error ->
                Log.i("teste", "getQuiz: ${error}")
            }
            .collect {
            if (it.status == ResponseWrapper.Status.SUCCESS) {
                val questsId = quizDb.getAllIds()
                val idQuest = Random.nextInt(0, questsId.size)
                val quiz = it.data!![idQuest]
                emit(res.handleSuccess(quiz))
            }else{
                Log.i("teste", "getQuiz: ${it}")
            }
        }
    }

    private fun getAllQuiz() = flow<ResponseWrapper<ArrayList<Quiz>>> {
        quizDb.delAllQuiz()
        firebaseRepository.getAllQuiz()
            .collect { quiz ->
                if (quiz.status == ResponseWrapper.Status.SUCCESS) {
                    quiz.data?.forEach {
                        quizDb.addQuiz(it)
                    }
                    emit(quiz)
                }
            }
    }

    fun getAllUserQuiz() = flow<ResponseWrapper<ArrayList<UserQuiz>>> {
        firebaseRepository.getAllUserQuiz().collect { userQuiz ->
            emit(userQuiz)
        }
    }
    fun addPointsQuiz() = flow<ResponseWrapper<ArrayList<UserQuiz>>> {
        firebaseRepository.addPointsQuiz().collect()
    }
}