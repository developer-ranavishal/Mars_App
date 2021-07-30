package com.example.mars_app.network

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
    val id: String,
    val img_src: String,
    val type: String,
    val price:Double) : Parcelable

