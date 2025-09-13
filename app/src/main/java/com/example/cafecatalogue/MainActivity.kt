package com.example.cafecatalogue

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.LinearLayout
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
    private lateinit var suburbSelectFragment: SuburbSelectFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("activity creation","creating main activity instance")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pageView = findViewById<ViewPager2>(R.id.MainViewPager)

        searchFragment = CafeSearchFragment()
        searchFragment.setRetainInstance(true)

        suburbSelectFragment = SuburbSelectFragment()

        val pageFragments = listOf(
            suburbSelectFragment,
            searchFragment
        )


        pageView.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = pageFragments.size
            override fun createFragment(position: Int) = pageFragments[position]
        }

        pageView.currentItem = 1
    }
}