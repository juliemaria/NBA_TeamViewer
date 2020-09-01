package com.example.nbateamviewer.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Teams(
        val wins: Int?,
        val losses: Int?,
        val full_name: String?,
        val id: Int?,
        val players: ArrayList<Players>?
) : Parcelable