package com.omarmattr.mcc_g_analytics.repository

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.omarmattr.mcc_g_analytics.model.Product
import com.omarmattr.mcc_g_analytics.model.Session

class MainActivityRepository private constructor() {
    private val db by lazy {
        FirebaseFirestore.getInstance()
    }
    fun getAllFood()=db.collection("food")
    fun getAllHP()=db.collection("hp")
    fun getAllPhones()=db.collection("phones")
    fun addSessionUser(session: Session)=db.collection("session").add(session)


    companion object {
        @Volatile
        private var instance: MainActivityRepository? = null
        private val LOCK = Any()

        operator fun invoke() =
            instance ?: synchronized(LOCK) {
                instance ?: createMainActivityRepository().also {
                    instance = it
                }
            }

        private fun createMainActivityRepository()= MainActivityRepository()
    }
}