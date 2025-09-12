package com.example.cafecatalogue

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Filter

class CafeSearchAdapter(context: Context, resource:Int, cafes: List<Cafe>) : ArrayAdapter<Cafe>(context, resource, cafes) {

    private val allCafes = ArrayList(cafes)
    private var selectedSuburbs: Set<Suburb> = emptySet()
    private var query = ""

    fun setSuburbFilter(suburbs: Set<Suburb>){
        Log.d("Search adapter","suburbs set to $suburbs")
        selectedSuburbs = suburbs
        filter.filter(query)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                query = constraint?.toString()?.lowercase() ?: ""

                Log.i("Search adapter - filter","filtering by query $query across suburbs $selectedSuburbs")
                val filtered = allCafes.filter { cafe ->
                    val matchesQuery = query.isEmpty() ||
                            cafe.name.lowercase().contains(query) ||
                            cafe.description.lowercase().contains(query)

                    val matchesSuburb = selectedSuburbs.isEmpty() ||
                            selectedSuburbs.contains(cafe.suburb)

                    matchesQuery && matchesSuburb
                }
                results.values = filtered
                results.count = filtered.size
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.d("Search adapter - filter","publishing results")
                clear()
                if (results?.values is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    Log.d("Search adapter - filter","search content ${results.values as List<Cafe>}")
                    @Suppress("UNCHECKED_CAST")
                    addAll(results.values as List<Cafe>)
                }
                notifyDataSetChanged()
            }
        }
    }
}