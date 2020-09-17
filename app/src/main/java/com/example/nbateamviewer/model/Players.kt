package com.example.nbateamviewer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players (
        val id: Int?,
        val first_name: String?,
        val last_name: String?,
        val position: String?,
        val number: Int?
) : Parcelable