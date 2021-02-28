package com.example.androiddevchallenge.ui.data

import android.os.Parcel
import android.os.Parcelable

data class DogBean(val name:String?, val character:String?, val head:String?, val pictures:List<String>?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(character)
        parcel.writeString(head)
        parcel.writeStringList(pictures)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DogBean> {
        override fun createFromParcel(parcel: Parcel): DogBean {
            return DogBean(parcel)
        }

        override fun newArray(size: Int): Array<DogBean?> {
            return arrayOfNulls(size)
        }
    }
}
