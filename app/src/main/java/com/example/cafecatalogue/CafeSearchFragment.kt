package com.example.cafecatalogue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CafeSearchFragment() : Fragment() {

    private val viewModel:SearchVM by activityViewModels()
    private lateinit var cafeSearchOptions: RecyclerView
    private lateinit var cafeSearchBar: SearchView
    private lateinit var favouriteBox:CheckBox
    private lateinit var adapter: CafeSearchRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.cafe_search_fragment, container, false)

        cafeSearchOptions = view.findViewById(R.id.SearchList)
        cafeSearchBar = view.findViewById(R.id.searchBar)
        favouriteBox = view.findViewById(R.id.favouriteFilterBox)
        adapter = CafeSearchRVAdapter(viewModel.filteredCafeList.value as ArrayList<Cafe>,viewModel)

        cafeSearchOptions.adapter = adapter
        cafeSearchOptions.layoutManager = LinearLayoutManager(requireContext())

        cafeSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search fragment - query text listener","submission detected")
                viewModel.setQuery(query?:"")
                return false
            }
            override fun onQueryTextChange(query:String?): Boolean {
                Log.d("Search fragment - query text listener","change detected")
                viewModel.setQuery(query?:"")
                return false
            }
        })

        favouriteBox.setOnCheckedChangeListener{ _ , isChecked ->
            viewModel.showOnlyFavourites(isChecked)
        }

        viewModel.filteredCafeList.observe(viewLifecycleOwner) {
            adapter.updateDisplay()
            Log.d("Search fragment","view model data query:${viewModel.query.value} :: suburbs:${viewModel.suburbs.value}")
        }

        viewModel.favouritesOnly.observe(viewLifecycleOwner) { enabled ->
            listAdapter.setFavouritesFilter(enabled)
            Log.d("Search fragment","view model data favourites:${viewModel.favouritesOnly.value}")
        }

        return view
    }
}