package com.example.cafecatalogue
import android.widget.ListView
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel

class SearchVM : ViewModel() {
    private lateinit var cafeList: ArrayList<Cafe>
}