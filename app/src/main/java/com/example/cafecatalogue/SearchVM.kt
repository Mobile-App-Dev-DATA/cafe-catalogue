package com.example.cafecatalogue
import android.util.Log
import android.widget.ListView
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel

class SearchVM : ViewModel() {
    private val _fullCafeList = MutableLiveData<ArrayList<Cafe>>(CafeList.generate())
    val fullCafeList: LiveData<ArrayList<Cafe>> = _fullCafeList

    private val _filteredCafeList = MutableLiveData<ArrayList<Cafe>>(fullCafeList.value)
    val filteredCafeList: LiveData<ArrayList<Cafe>> = _filteredCafeList
    private var showingOnlyFavourites:Boolean = false

    private val _favouriteCafeList = MutableLiveData<ArrayList<String>>(ArrayList())
    val favouriteCafeList: LiveData<ArrayList<String>> = _favouriteCafeList

    private val _query = MutableLiveData<String>("")
    val query: LiveData<String> = _query

    private val _suburbs = MutableLiveData<Set<Suburb>>(emptySet())
    val suburbs: LiveData<Set<Suburb>> = _suburbs

    val selectedCafe = MutableLiveData<Cafe>(null)

    fun setSuburbs(s:Set<Suburb>) {
        filterCafeList(query.value?:"",s)
        _suburbs.value = s
    }

    fun setQuery(q:String){
        filterCafeList(q, suburbs.value?:emptySet())
        _query.value = q
    }

    fun setFavourite(cafe:Cafe, isFavourite:Boolean){
        if (isFavourite){
            _favouriteCafeList.value = favouriteCafeList.value!!.plus(cafe.name) as ArrayList<String>
            Log.i("Search VM","$cafe set to favourite")
        }else{
            _favouriteCafeList.value = favouriteCafeList.value!!.filter{ it != cafe.name } as ArrayList<String>
            Log.i("Search VM","$cafe set to not favourite")
        }
        if(showingOnlyFavourites){
            filterCafeList()
        }
    }

    fun updateFavouriteList(newFavourites:ArrayList<String>){
        _favouriteCafeList.value = newFavourites
    }

    fun showOnlyFavourites(b:Boolean){
        val hasChanged = b != showingOnlyFavourites
        showingOnlyFavourites = b
        if (hasChanged) {
            filterCafeList()
        }
    }

    fun isFavourite(cafe:Cafe): Boolean {
        return favouriteCafeList.value?.contains(cafe.name) == true
    }

    private fun filterCafeList() {
        filterCafeList(query.value?:"", suburbs.value?:emptySet())
    }

    private fun filterCafeList(q:String, s:Set<Suburb>) {
        val newFilteredCafes:ArrayList<Cafe> = ArrayList<Cafe>()
        val favouritesValue = favouriteCafeList.value?: ArrayList<Favourite>()
        Log.i("Search VM","starting filtering")
        fullCafeList.value?.forEach {
            if(q in it.name){
                if(s.isEmpty() || it.suburb in s) {
                    if(!showingOnlyFavourites || it.name in favouritesValue) {
                        Log.d("Search VM - Filtering","accepted $it")
                        newFilteredCafes.add(it)
                    }else{Log.d("Search VM - Filtering","rejected $it due to favouritism")}
                }else{Log.d("Search VM - Filtering","rejected $it due to suburb")}
            }else{Log.d("Search VM - Filtering","rejected $it due to name")}
        }
        Log.i("Search VM - Filtering","filtering complete")
        _filteredCafeList.value = newFilteredCafes
    }
}