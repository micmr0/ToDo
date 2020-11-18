package com.micmr0.todo

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import java.util.*

public class Todo() : Parcelable{
    @DocumentId
    val documentId: String = ""
    var icon : String? = ""
    var title : String? = ""
    var description : String? = ""
    var date : Date? = null

    constructor(parcel: Parcel) : this() {
        icon = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(icon)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}