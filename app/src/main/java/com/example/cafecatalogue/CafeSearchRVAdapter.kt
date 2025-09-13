package com.example.cafecatalogue

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class CafeSearchRVAdapter(
    private var cafes: ArrayList<Cafe>,
    private val viewModel:SearchVM
): RecyclerView.Adapter<CafeSearchRVAdapter.CafeViewHolder>() {

    class CafeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nameDisplay: TextView = view.findViewById(R.id.CafeName)
        val favouriteBox: CheckBox = view.findViewById(R.id.Favourite_box)
        val clickableSpace: LinearLayout = view.findViewById(R.id.clickableSpace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cafe_search_display, parent, false)
        return CafeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val cafe = cafes[position]
        holder.nameDisplay.text = cafe.name

        holder.favouriteBox.setOnCheckedChangeListener(null)
        holder.favouriteBox.isChecked = viewModel.isFavourite(cafe)

        holder.favouriteBox.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setFavourite(cafe, isChecked)
        }

        holder.clickableSpace.setOnClickListener{
            viewModel.selectedCafe.value = cafe
        }
    }

    override fun getItemCount(): Int {
        return cafes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDisplay(){
        cafes = viewModel.filteredCafeList.value?: ArrayList<Cafe>()
        notifyDataSetChanged()
        Log.i("cafe search VM adapter","Displaying $cafes")
    }
}