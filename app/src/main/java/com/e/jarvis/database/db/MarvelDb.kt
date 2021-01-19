package com.e.jarvis.database.db

import com.e.jarvis.database.dao.MarvelDao
import com.e.jarvis.models.generics.GenericResults

class MarvelDb(val marvelDao: MarvelDao) {
    //tabela results
    fun getAll() = marvelDao.getAll()

    fun getById(id: String) = marvelDao.getById(id)

    suspend fun add(results: GenericResults) = marvelDao.add(results)

    suspend fun delete(results: GenericResults) = marvelDao.delete(results)


}