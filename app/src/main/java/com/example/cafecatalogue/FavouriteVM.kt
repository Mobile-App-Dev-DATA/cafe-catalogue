package com.example.cafecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteVM : ViewModel() {
    private val _favourite_set = MutableLiveData<Set<Favourite>>(emptySet())
    val favourite_set : LiveData<Set<Favourite>> = _favourite_set

    /*
     * intended for use upon activity start to initialise favourite set
     * for adding or removing from favourites, use other methods
     */
    fun set_favourites(favourites: Set<Favourite>)
    {
        _favourite_set.value = favourites
    }

    /*
     * One of three methods for setting favourite, depending on personal preference
     * This one can set to either using boolean parameter
     */
    fun setFavouriteForCafe(cafe:Cafe, isFavourite: Boolean){
        if (isFavourite) {
            makeCafeFavourite(cafe)
        }else{
            makeCafeNotFavourite(cafe)
            }
        }

    /*
     * One of three methods for setting favourite, depending on personal preference
     * This method sets a cafe to favourite
     */
    fun makeCafeFavourite(cafe:Cafe) {
        @Suppress("UNCHECKED_CAST")
        set_favourites(favourite_set.value?.plus(cafe.name) as Set<Favourite> ?: emptySet())
    }

    /*
     * One of three methods for setting favourites, depending on personal preference
     * This method sets a cafe to not favourite
     */
    fun makeCafeNotFavourite(cafe:Cafe) {
        set_favourites(favourite_set.value?.filter { it.name != cafe.name }?.toSet() ?: emptySet())
    }
}