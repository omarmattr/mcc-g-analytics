package com.omarmattr.mcc_g_analytics

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.omarmattr.mcc_g_analytics.model.Product
import com.omarmattr.mcc_g_analytics.model.Session
import com.omarmattr.mcc_g_analytics.repository.MainActivityRepository
import com.omarmattr.mcc_g_analytics.uitls.MyResult


class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainActivityRepository()
    private val foodLiveData: MutableLiveData<MyResult<Any>> = MutableLiveData()
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(application)

    fun onClickEvent (uId:String,uName:String,name:String,event:String){
        firebaseAnalytics.logEvent(event) {
            param("user id", uId)
            param("user name", uName)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
        }
    }


    fun getAllFood() {
        foodLiveData.postValue(MyResult.loading(0))
        repository.getAllFood().addSnapshotListener { value, error ->
            if (error == null) {
                val array = arrayListOf<Product>()
                if (value != null)
                    for (v in value.documents) array.add(v.toObject(Product::class.java)!!)
                foodLiveData.postValue(MyResult.success(array))
            } else foodLiveData.postValue(MyResult.error(error.message, 0))

        }
    }
    fun addSessionUser(session: Session)=repository.addSessionUser(session)
    fun getAllHP() {
        foodLiveData.postValue(MyResult.loading(0))
        repository.getAllHP().addSnapshotListener { value, error ->
            if (error == null) {
                val array = arrayListOf<Product>()
                if (value != null)
                    for (v in value.documents) array.add(v.toObject(Product::class.java)!!)
                foodLiveData.postValue(MyResult.success(array))
            } else foodLiveData.postValue(MyResult.error(error.message, 0))

        }
    }

    fun getAllPhones() {
        foodLiveData.postValue(MyResult.loading(0))
        repository.getAllPhones().addSnapshotListener { value, error ->
            if (error == null) {
                val array = arrayListOf<Product>()
                if (value != null)
                    for (v in value.documents) array.add(v.toObject(Product::class.java)!!)
                foodLiveData.postValue(MyResult.success(array))
            } else foodLiveData.postValue(MyResult.error(error.message, 0))
        }
    }

    fun getFoodLiveData() = foodLiveData

}