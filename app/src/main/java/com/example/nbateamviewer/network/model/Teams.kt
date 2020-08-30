package com.example.nbateamviewer.network.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Teams(
        val wins: Int?,
        val losses: Int?,
        val full_name: String?,
        val id: Int?,
        val players: ArrayList<Players>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(Players)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(wins)
        parcel.writeValue(losses)
        parcel.writeString(full_name)
        parcel.writeValue(id)
        parcel.writeTypedList(players)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Teams> {
        override fun createFromParcel(parcel: Parcel): Teams {
            return Teams(parcel)
        }

        override fun newArray(size: Int): Array<Teams?> {
            return arrayOfNulls(size)
        }
    }

}