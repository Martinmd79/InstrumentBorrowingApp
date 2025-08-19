package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val name: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val colorOptions: List<String>,
    val image: Int
):Parcelable