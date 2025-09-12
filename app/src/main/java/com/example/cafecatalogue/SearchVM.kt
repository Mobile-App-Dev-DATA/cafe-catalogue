package com.example.cafecatalogue
import android.widget.ListView
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel

class SearchVM : ViewModel() {
    private val _query = MutableLiveData<String>("")
    val query: LiveData<String> = _query

    private val _suburbs = MutableLiveData<Set<Suburb>>(emptySet())
    val suburbs: LiveData<Set<Suburb>> = _suburbs

    private val _favouritesOnly = MutableLiveData<Boolean>(false)
    val favouritesOnly: LiveData<Boolean> = _favouritesOnly

    fun setSuburbs(s:Set<Suburb>) {
        _suburbs.value = s
    }

    fun setQuery(q:String){
        _query.value = q
    }

    fun setFavouritesOnly(enabled:Boolean){
        _favouritesOnly.value = enabled

    }
}