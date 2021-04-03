package com.omarmattr.mcc_g_analytics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val name:String="" ,val img:String="",val attr1:String="",val attr2:String=""):Parcelable
