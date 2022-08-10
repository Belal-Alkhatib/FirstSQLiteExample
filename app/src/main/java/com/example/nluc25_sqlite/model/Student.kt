package com.example.nluc25_sqlite.model

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable

data class Student(var id:Int, var name:String? , var average:Double):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(average)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
       const val TABLE_NAME = "student"
       const val COL_ID = "id"
       const val COL_NAME = "name"
       const val COL_AVG = "average"

        const val TABLE_CREATE = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NAME TEXT NOT NULL, $COL_AVG DOUBLE)"

        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }


}

