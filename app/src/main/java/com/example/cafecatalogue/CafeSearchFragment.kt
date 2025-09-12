package com.example.cafecatalogue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class CafeSearchFragment(private val cafes: ArrayList<Cafe>) : Fragment() {

    private val viewModel:SearchVM by activityViewModels()
    private lateinit var cafeListView: ListView
    private lateinit var cafeSearchBar: SearchView
    private lateinit var listAdapter: CafeSearchAdapter
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
                Log.d("Search fragment - query text listener","submission detected")
                cachedQuery = query?:""
                Toast.makeText(requireContext(), "Searched for $cachedQuery", Toast.LENGTH_LONG).show()
                return false
            }
            override fun onQueryTextChange(query:String?): Boolean {
                Log.d("Search fragment - query text listener","change detected")
                viewModel.setQuery(query?:"")
                return false
            }
        })

        viewModel.query.observe(viewLifecycleOwner) { query ->
            listAdapter.filter.filter(query)
            Log.d("Search fragment","view model data query:${viewModel.query.value} :: suburbs:${viewModel.suburbs.value}")
        }

        viewModel.suburbs.observe(viewLifecycleOwner) { suburbs ->
            listAdapter = CafeSearchAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                ArrayList(cafes.map {it.copy()})
            )
            cafeListView.adapter = listAdapter
            listAdapter.setSuburbFilter(suburbs)
            Log.d("Search fragment","view model data suburbs:${viewModel.suburbs.value}")
        }

        viewModel.favouritesOnly.observe(viewLifecycleOwner) { enabled ->
            listAdapter.setFavouritesFilter(enabled)
            Log.d("Search fragment","view model data favourites:${viewModel.favouritesOnly.value}")
        }

        return view
    }
}