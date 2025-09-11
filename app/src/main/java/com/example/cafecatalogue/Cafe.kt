package com.example.cafecatalogue
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cafe(val name:String, val suburb:Suburb, val description:String) : Parcelable