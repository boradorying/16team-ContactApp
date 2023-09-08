package com.example.contacts

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var name: String,
    var phoneNumber: String,
    var email: String?,
    var profileImageUri: Uri?,
    var photo: Int,
    var bookmark: Boolean,
    var isNew: Boolean,
    var isCilked: Boolean
) : Parcelable
