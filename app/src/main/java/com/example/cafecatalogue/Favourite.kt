package com.example.cafecatalogue
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favourite(val name:String) : Parcelable
