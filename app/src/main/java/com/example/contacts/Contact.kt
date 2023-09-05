package com.example.contacts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val photo: Int,
    var bookmark : Boolean
) : Parcelable
