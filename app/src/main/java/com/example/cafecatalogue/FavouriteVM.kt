package com.example.cafecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteVM : ViewModel() {
    private val _favourite_list = MutableLiveData<List<Favourite>>(emptyList())
    val favourite_list : LiveData<List<Favourite>> = _favourite_list

    fun set_list(in_list : List<Favourite>)
    {
        _favourite_list.value = in_list
    }

    fun favourite(name : String)
    {
    }
}