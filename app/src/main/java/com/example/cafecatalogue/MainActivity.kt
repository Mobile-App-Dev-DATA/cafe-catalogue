package com.example.cafecatalogue

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var searchFragment: CafeSearchFragment
    private lateinit var suburbSelectFragment: SuburbSelectFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("activity creation","creating main activity instance")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val viewModel:SearchVM by viewModels()

        var favouriteList = intent.getStringArrayListExtra("favourites")?:ArrayList<String>()
        if (favouriteList.isEmpty()) {
            favouriteList = savedInstanceState?.getStringArrayList("favourites")?:ArrayList<String>()
        }
        if (favouriteList.isNotEmpty()) {
            viewModel.updateFavouriteList(favouriteList)
        }


        val pageView = findViewById<ViewPager2>(R.id.MainViewPager)

        searchFragment = CafeSearchFragment()

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

        viewModel.selectedCafe.observe(this) {
            /*
             * the comments are code that will only be able to work once the exploded view is written
             */
            val selectedCafe = viewModel.selectedCafe.value
            if (selectedCafe == null){
                Log.e("Main activity - activity change","the selected cafe resolved to null.  this should not be possible")
            }else{
                val intent:Intent = Intent(this, Exploded_view::class.java)
                val bundle:Bundle = Bundle()
                bundle.putStringArrayList("favourites",viewModel.favouriteCafeList.value)
                bundle.putParcelable("cafe",selectedCafe)
                Log.i("Main activity - activity change","swapping to the exploded activity with data $bundle")
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}