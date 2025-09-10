package com.example.cafecatalogue

import android.graphics.drawable.Drawable

data class Cafe(val name:String, val suburb:Suburb, val description:String, val image:Drawable, val isFavourite:Boolean = false)
