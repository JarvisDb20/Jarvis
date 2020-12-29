package com.e.jarvis.repository

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class KeyHash(val privateKey: String, val publicKey: String, var ts: String = "") {
    private fun updateTs() {
        ts = Timestamp(System.currentTimeMillis()).toString()
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    init {
        updateTs()
    }

    fun getKey(): String {
        val saida = ts + privateKey + publicKey
        return saida.md5()
    }
}