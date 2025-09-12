package com.example.cafecatalogue

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var searchFragment: CafeSearchFragment
    private lateinit var cafeListView: ListView
    private lateinit var cafeSearchBar: SearchView
    private lateinit var listAdapter: CafeSearchAdapter
    private lateinit var cafeList: ArrayList<Cafe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pageView = findViewById<ViewPager2>(R.id.MainViewPager)

        searchFragment = CafeSearchFragment(CafeList.generate())

        val pageFragments = listOf(
            SuburbSelectFragment(),
            searchFragment
        )

        pageView.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = pageFragments.size
            override fun createFragment(position: Int) = pageFragments[position]
        }

        pageView.currentItem = 1
    }

    fun updateSuburbFilter(suburbs: Set<Suburb>) {
        searchFragment.updateSuburbFilter(suburbs)
    }
}