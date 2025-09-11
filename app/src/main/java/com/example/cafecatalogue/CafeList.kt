package com.example.cafecatalogue

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CafeList {

    private val cafes = MutableLiveData<List<Cafe>>(emptyList())
    private var onlyShowFavourites:Boolean = false

    init {
        add("Hinata Cafe", Suburb.FREMANTLE, "TODO")
        add("Kith Eatery", Suburb.NEDLANDS, "TODO")
        add("Lo Quay River Cafe", Suburb.WILSON,
            "A scenic cafe by the river with an indoors and outdoors area to enjoy the view of swans flying by and birds spying for some food. During winter, outdoor heating appliances are provided to warm guests under their veranda, allowing a free open space for chats with family and mates. It looks amazing but that’s about it, the food was not the greatest thing in the world.\n" +
                    "\n" +
                    "1/5 food and drinks\n" +
                    "4/5 atmosphere\n" +
                    "\n" +
                    "TODO: implement images")
        add("Sinnamon", Suburb.BENTLEY, "TODO")
    }

    fun add(cafe:Cafe){
        cafes.value = cafes.value.orEmpty() + cafe
    }
    fun add(name:String, suburb:Suburb, description:String){
        add(Cafe(name, suburb, description))
    }

    fun setOnlyShowFavourites(v:Boolean){
        onlyShowFavourites = v
    }
    fun getOnlyShowFavourites(v:Boolean): Boolean{
        return onlyShowFavourites
    }

    fun get(): List<Cafe> {
        // get all cafes
        return get("")
    }
    fun get(nameFilter:String): List<Cafe> {
        // get all cafes who's name contains the filter (substring)
        return get(nameFilter, Suburb.entries.toList())
    }
    fun get(suburbFilter: Suburb): List<Cafe> {
        // get all cafes who are located in the suburb
        return get(listOf(suburbFilter))
    }
    fun get(suburbFilter: List<Suburb>): List<Cafe> {
        // get all cafes who are located in the suburbs
        return get("", suburbFilter)
    }
    fun get(nameFilter:String, suburbFilter:Suburb): List<Cafe> {
        // get all cafes who are located in the suburb and contain the filter (substring)
        return get(nameFilter, listOf(suburbFilter))
    }

    fun get(nameFilter:String, suburbFilter:List<Suburb>): List<Cafe> {
        // get all cafes who are located in the suburbs and contain the filter (substring)

        val filteredList = mutableListOf<Cafe>()

        cafes.value.orEmpty().forEach {
            if (it.name.contains(nameFilter) && suburbFilter.contains(it.suburb)) {
                if (!onlyShowFavourites || it.isFavourite) {
                    filteredList.add(it)
                }
            }
        }

        return filteredList
    }

    fun size(): Int{
        return cafes.value.orEmpty().size
    }
}