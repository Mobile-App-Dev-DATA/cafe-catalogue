package com.example.cafecatalogue

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class CafeSearchAdapter(context: Context, reasourse:Int, cafes: List<Cafe>) :
    ArrayAdapter<Cafe>(context, reasourse, cafes) {

    private val allCafes = ArrayList(cafes)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    results.values = allCafes
                    results.count = allCafes.size
                } else {
                    val query = constraint.toString().lowercase()
                    val filtered = allCafes.filter { it.name.lowercase().contains(query) }
                    results.values = filtered
                    results.count = filtered.size
                }
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