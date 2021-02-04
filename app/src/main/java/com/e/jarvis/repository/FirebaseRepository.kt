package com.e.jarvis.repository

import com.e.jarvis.models.ResponseHandler
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.models.modelsQuiz.UserQuiz
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val responseHandler: ResponseHandler,
    private val logout: Task<Void>
//    private val preferences: SharedPreferences
) {

    private val FAVORITOS = "Favorites"
    private val QUIZ = "Quiz"
    private val USER = "User"

    fun getLogged(): Flow<Boolean> = flow { emit(auth.currentUser != null) }

    suspend fun logOut() {
        auth.signOut()
        logout.await()
    }

    fun addFavorite(fav: Favorito) = flow<ResponseWrapper<Boolean>> {
        val saida: ResponseWrapper<Boolean> = responseHandler.handleLoading("Sending info...")
        try {
            emit(saida)
            fav.id = fav.results!!.id
            db.collection(USER).document(auth.currentUser!!.uid).collection(FAVORITOS).document(fav.id)
                .set(fav)
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            emit(responseHandler.handleException<Boolean>(e))
        }
    }

    fun removeFavorite(fav: Favorito) = flow<ResponseWrapper<Boolean>> {
        val saida: ResponseWrapper<Boolean> = responseHandler.handleLoading("Sending info...")
        try {
            emit(saida)
            fav.id = fav.results!!.id
            db.collection(USER).document(auth.currentUser!!.uid).collection(FAVORITOS).document(fav.id)
                .delete()
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            emit(responseHandler.handleException<Boolean>(e))
        }
    }

    fun getFavorite() = flow<ResponseWrapper<ArrayList<Favorito>>> {
        val saida: ResponseWrapper<ArrayList<Favorito>> =
            responseHandler.handleLoading("Retrieving games...")
        try {
            emit(saida)
            val listGames = ArrayList<Favorito>()
            val games =
                db.collection(USER).document(auth.currentUser!!.uid).collection(FAVORITOS).get().await()

            games.forEach { doc ->
                listGames.add(doc.toObject<Favorito>())
            }
            emit(responseHandler.handleSuccess(listGames))
        } catch (e: Exception) {
            emit(responseHandler.handleException<ArrayList<Favorito>>(e))
        }
    }

    // add e update
    fun updateUserQuiz(userQuiz: UserQuiz) = flow<ResponseWrapper<Boolean>> {
        val saida: ResponseWrapper<Boolean> = responseHandler.handleLoading("Sending info...")
        try {
            emit(saida)
            db.collection(USER).document(auth.currentUser!!.uid).collection(QUIZ).document(auth.currentUser!!.uid)
                .set(userQuiz)
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            emit(responseHandler.handleException<Boolean>(e))
        }
    }

    fun addQuiz(quiz: Quiz) = flow<ResponseWrapper<Boolean>> {
        val saida: ResponseWrapper<Boolean> = responseHandler.handleLoading("Sending info...")
        try {
            emit(saida)
            quiz.id = 1
            db.collection(QUIZ).document(quiz.id.toString()).set(quiz)
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            emit(responseHandler.handleException<Boolean>(e))
        }
    }

    fun getAllQuiz() = flow<ResponseWrapper<ArrayList<Quiz>>>{
        val saida: ResponseWrapper<ArrayList<Quiz>> =
            responseHandler.handleLoading("Retrieving games...")
        try {
            emit(saida)
            val listGames = ArrayList<Quiz>()
            val games = db.collection(QUIZ).get().await()
            games.forEach { doc ->
                listGames.add(doc.toObject<Quiz>())
            }
            emit(responseHandler.handleSuccess(listGames))
        } catch (e: Exception) {
            emit(responseHandler.handleException<ArrayList<Quiz>>(e))
        }
    }

    fun getAllUserQuiz() = flow<ResponseWrapper<ArrayList<UserQuiz>>>{
        val saida: ResponseWrapper<ArrayList<UserQuiz>> =
            responseHandler.handleLoading("Retrieving games...")
        try {
            emit(saida)
            val listGames = ArrayList<UserQuiz>()
            val games = db.collection(USER).document(auth.currentUser!!.uid).collection(QUIZ).get().await()
            games.forEach { doc ->
                listGames.add(doc.toObject<UserQuiz>())
            }
            emit(responseHandler.handleSuccess(listGames))
        } catch (e: Exception) {
            emit(responseHandler.handleException<ArrayList<UserQuiz>>(e))
        }
    }

    private fun getUniqueKey() = db.collection("key").document().id

}