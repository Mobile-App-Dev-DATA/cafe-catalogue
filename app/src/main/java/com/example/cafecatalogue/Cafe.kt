package com.example.cafecatalogue

import android.graphics.drawable.Drawable

data class Cafe(val name:String, val suburb:Suburb, val description:String, val isFavourite:Boolean = false){
    override fun toString(): String {
        return name
    }
}
