package com.e.jarvis.repository

import com.e.jarvis.models.ResponseHandler
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.UserModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepository(
    private val db: FirebaseFirestore,
    private val store: FirebaseStorage,
    private val auth: FirebaseAuth,
    private val responseHandler: ResponseHandler,
//    private val preferences: SharedPreferences
) {
    //    private val LOGGED = "logged"
//    private val COLL = "Games"
//


    fun createUser(userModel: UserModel): Flow<ResponseWrapper<UserModel>> = flow {
        var saida: ResponseWrapper<UserModel> = responseHandler.handleLoading("Creating user...")
        try {
            emit(saida)
            val createTask =
                auth.createUserWithEmailAndPassword(userModel.email, userModel.password!!).await()
            saida = getLoggedUser(createTask)
            emit(saida)
        } catch (e: Exception) {
            saida = responseHandler.handleException(e)
            emit(saida)
        }
    }

    fun getLogged(): Flow<Boolean> = flow { emit(auth.currentUser != null) }

    fun logOut() {
        auth.signOut()
    }

    fun logIn(userModel: UserModel): Flow<ResponseWrapper<UserModel>> = flow {
        var saida: ResponseWrapper<UserModel> = responseHandler.handleLoading("Creating user...")
        try {
            emit(saida)
            val logTask =
                auth.signInWithEmailAndPassword(userModel.email, userModel.password!!).await()
            saida = getLoggedUser(logTask)
            emit(saida)
        } catch (e: Exception) {
            saida = responseHandler.handleException(e)
            emit(saida)
        }

    }

    private fun getLoggedUser(
        task: AuthResult
    ): ResponseWrapper<UserModel> {
        return responseHandler.handleSuccess(
            UserModel(
                task.user?.email!!,
                "",
                task.user?.uid
            )
        )
    }

    private fun getUniqueKey() = db.collection("key").document().id

}