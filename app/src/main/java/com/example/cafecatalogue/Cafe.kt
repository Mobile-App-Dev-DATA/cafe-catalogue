package com.example.cafecatalogue
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import android.graphics.drawable.Drawable

@Parcelize
data class Cafe(val name:String, val suburb:Suburb, val description:String, val isFavourite:Boolean = false) : Parcelable
{
    override fun toString(): String {
        return name
    }
}