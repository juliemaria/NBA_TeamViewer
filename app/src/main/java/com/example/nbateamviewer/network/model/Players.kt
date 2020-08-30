package com.example.nbateamviewer.network.model

import android.os.Parcel
import android.os.Parcelable

data class Players (
        val id: Int?,
        val first_name: String?,
        val last_name: String?,
        val position: String?,
        val number: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(position)
        parcel.writeValue(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Players> {
        override fun createFromParcel(parcel: Parcel): Players {
            return Players(parcel)
        }

        override fun newArray(size: Int): Array<Players?> {
            return arrayOfNulls(size)
        }
    }
}