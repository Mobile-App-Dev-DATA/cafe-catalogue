package com.example.cafecatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CafeSearchFragment(private val cafes: ArrayList<Cafe>) : Fragment() {

    private lateinit var cafeListView: ListView
    private lateinit var cafeSearchBar: SearchView
    private lateinit var listAdapter: CafeSearchAdapter
    private lateinit var cafeList: ArrayList<Cafe>
    private var cachedQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.cafe_search_fragment, container, false)

        cafeListView = view.findViewById(R.id.SearchList)
        cafeSearchBar = view.findViewById(R.id.searchBar)

        listAdapter = CafeSearchAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            cafes
        )
        cafeListView.adapter = listAdapter

        cafeSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cachedQuery = query?:""
                Toast.makeText(requireContext(), "Searched for $cachedQuery", Toast.LENGTH_LONG).show()
                return false
            }
            override fun onQueryTextChange(query:String?): Boolean {
                cachedQuery = query?:""
                listAdapter.filter.filter(cachedQuery)
                return false
            }
        })
        return view
    }

    fun updateSuburbFilter(suburbs: Set<Suburb>) {
        listAdapter = CafeSearchAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            cafes
        )
        listAdapter.setSuburbFilter(suburbs)
        listAdapter.filter.filter(cachedQuery)
        cafeListView.adapter = listAdapter
    }
}