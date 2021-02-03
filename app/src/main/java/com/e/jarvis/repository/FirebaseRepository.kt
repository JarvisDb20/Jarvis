package com.e.jarvis.repository

import android.util.Log
import com.e.jarvis.models.ResponseHandler
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val responseHandler: ResponseHandler,
    private val logout : Task<Void>
//    private val preferences: SharedPreferences
) {

    private val COLL = "Favorites"
    private val USER = "User"

    fun getLogged(): Flow<Boolean> = flow { emit(auth.currentUser != null) }

    suspend fun logOut() {
        auth.signOut()
        logout.await()
    }

    fun addFavorite(fav: Favorito): Flow<ResponseWrapper<Boolean>> = flow {
        val saida: ResponseWrapper<Boolean> = responseHandler.handleLoading("Sending info...")
        try {
            emit(saida)
            fav.id = getUniqueKey()
            db.collection(USER).document(auth.currentUser!!.uid).collection(COLL).document(fav.id)
                .set(fav)
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            emit(responseHandler.handleException<Boolean>(e))
        }
    }
    fun getFavorite(): Flow<ResponseWrapper<ArrayList<Favorito>>> = flow {
        val saida: ResponseWrapper<ArrayList<Favorito>> =
            responseHandler.handleLoading("Retrieving games...")
        try {
            emit(saida)
            val listGames = ArrayList<Favorito>()
            val games = db.collection(USER).document(auth.currentUser!!.uid).collection(COLL).get().await()

            games.forEach { doc ->
                listGames.add(doc.toObject<Favorito>())
            }
            emit(responseHandler.handleSuccess(listGames))
        } catch (e: Exception) {
            emit(responseHandler.handleException<ArrayList<Favorito>>(e))
        }

    }
    private fun getUniqueKey() = db.collection("key").document().id

}