package com.example.andersen_homework_5

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val surName: String,
    val phone: String,
    val id: Int
) : Parcelable