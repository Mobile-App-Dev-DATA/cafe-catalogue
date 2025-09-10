package com.example.cafecatalogue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class CafeListFragment : Fragment(R.layout.cafe_list_fragment) {
    private val viewModel: CafeListViewModel by activityViewModels()
    private lateinit var adapter: CafeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listFragmentView = view.findViewById<RecyclerView>(R.id.cafeListRecycler)
        adapter = CafeAdapter(viewModel.cafes, view.context)
        listFragmentView.layoutManager = LinearLayoutManager(requireContext())
        listFragmentView.adapter = adapter
/*
This doesn't work as CafeList class isn't LiveData.  will have to find work around if app is
to support live updates

        viewModel.cafes.observe(viewLifecycleOwner){   list ->
            adapter.submitList(list)
        }
*/
    }
}