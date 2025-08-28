package com.example.cafecatalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CafeAdapter(
    private var cafes: CafeList,
    private val context: Context
): RecyclerView.Adapter<CafeAdapter.CafeViewHolder>() {
    class CafeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameTxtBox:TextView = view.findViewById(R.id.Name)
        val suburbTxtBox:TextView = view.findViewById(R.id.Suburb)
        val descriptionTxtBox:TextView = view.findViewById(R.id.Description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cafe_list_entry, parent, false)
        return CafeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CafeViewHolder,
        position: Int
    ) {
        val cafe =
            cafes.get()[position]// TODO: this is hellishly inefficient.  this must be changed when search filtering is integrated
        holder.nameTxtBox.text = cafe.name
        holder.suburbTxtBox.text = cafe.suburb.name.lowercase().replaceFirstChar { it.uppercase() }
        holder.descriptionTxtBox.text = cafe.description
    }

    override fun getItemCount(): Int {
        return cafes.size()
    }
}