package com.example.cafecatalogue

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class CafeSearchAdapter(context: Context, resource:Int, cafes: List<Cafe>) :
    ArrayAdapter<Cafe>(context, resource, cafes) {

    private val allCafes = ArrayList(cafes)
    private var selectedSuburbs: Set<Suburb> = emptySet()

    fun setSuburbFilter(suburbs: Set<Suburb>){
        selectedSuburbs = suburbs
        filter.filter("")
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val query = constraint?.toString()?.lowercase() ?: ""

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
                clear()
                if (results?.values is List<*>) {
                    addAll(results.values as List<Cafe>)
                }
                notifyDataSetChanged()
            }
        }
    }
}