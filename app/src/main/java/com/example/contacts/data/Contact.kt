package com.example.contacts

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val profileImageUri: Uri?,
    var photo: Int,
    var bookmark: Boolean,
    var isNew: Boolean,
    var isCilked: Boolean
) : Parcelable
