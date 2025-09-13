package com.example.cafecatalogue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteVM : ViewModel() {
    private val _favourite_set = MutableLiveData<ArrayList<String>>(ArrayList())
    val favourite_set: LiveData<ArrayList<String>> = _favourite_set

    /*
     * intended for use upon activity start to initialise favourite set
     * for adding or removing from favourites, use other methods
     */
    fun set_favourites(favourites: ArrayList<String>) {
        _favourite_set.value = favourites
    }

    /*
     * One of three methods for setting favourite, depending on personal preference
     * This one can set to either using boolean parameter
     */
    fun setFavourite(cafe: Cafe?, isFavourite: Boolean) {
        if (isFavourite) {
            _favourite_set.value = favourite_set.value!!.plus(cafe?.name) as ArrayList<String>
            Log.i("Search VM", "$cafe set to favourite")
        } else {
            _favourite_set.value =
                favourite_set.value!!.filter { it != cafe?.name } as ArrayList<String>
            Log.i("Search VM", "$cafe set to not favourite")
        }
    }
}
//    /*
//     * One of three methods for setting favourite, depending on personal preference
//     * This method sets a cafe to favourite
//     */
//    fun makeCafeFavourite(cafe:Cafe?) {
//        @Suppress("UNCHECKED_CAST")
//        set_favourites(favourite_set.value?.plus(cafe?.name) as Set<Favourite> ?: emptySet())
//    }
//
//    /*
//     * One of three methods for setting favourites, depending on personal preference
//     * This method sets a cafe to not favourite
//     */
//    fun makeCafeNotFavourite(cafe:Cafe?) {
//        set_favourites(favourite_set.value?.filter { it.name != cafe?.name }?.toSet() ?: emptySet())
//    }
//}