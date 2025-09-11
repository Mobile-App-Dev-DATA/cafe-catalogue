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

class MainActivity : AppCompatActivity() {

    private lateinit var cafeListView: ListView
    private lateinit var cafeSearchBar: SearchView
    private lateinit var listAdapter: CafeSearchAdapter
    private lateinit var cafeList: ArrayList<Cafe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        cafeListView = findViewById(R.id.SearchList)
        cafeSearchBar = findViewById(R.id.searchBar)

        cafeList = ArrayList()
        cafeList.add(Cafe("home cafe", Suburb.BENTLEY, "a nice homely cafe", true))
        cafeList.add(Cafe("hommie spot", Suburb.NEDLANDS, "a spot for the hommies to drink coffee", true))
        cafeList.add(Cafe("shit cafe", Suburb.FREMANTLE, "a shit cafe.  do not go here", false))
        cafeList.add(Cafe("imposter cafe", Suburb.WILSON, "kinda sus", false))

        listAdapter = CafeSearchAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cafeList
        )
        cafeListView.adapter = listAdapter

        cafeSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "searched for $query", Toast.LENGTH_LONG)
                    .show()
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                listAdapter.filter.filter(query)
                return false
            }
        })
    }
}