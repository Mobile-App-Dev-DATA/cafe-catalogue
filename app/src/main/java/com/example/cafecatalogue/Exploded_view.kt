package com.example.cafecatalogue

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.util.Log


class Exploded_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exploded_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("ExplodedView", "Activity started")

        // Get xml data
        val cafe_name = findViewById<TextView>(R.id.cafe_name)
        val cafe_suburb = findViewById<TextView>(R.id.cafe_suburb)
        val cafe_description = findViewById<TextView>(R.id.cafe_description)
        val cafe_image = findViewById<ImageView>(R.id.cafe_image)
        val favourite_button = findViewById<Button>(R.id.favourite_button)
        val back_button = findViewById<Button>(R.id.back_button)

        Log.d("ExplodedView", "Views found successfully")

        // Obtain cafe object from bundle
        val bundle = intent.extras
        Log.d("ExplodedView", "Bundle: $bundle")

        val receivedCafe = bundle?.getParcelable("cafe",Cafe::class.java)
        val receievedFavourite = bundle?.getStringArrayList("favourites")?:ArrayList<String>()

        Log.d("ExplodedView", "Received cafe: $receivedCafe")
        Log.d("ExplodedView", "Received favourites: $receievedFavourite")

        if (receivedCafe == null) {
            Log.e("ExplodedView", "Cafe object is null!")
            finish()
            return
        }

        Log.d("ExplodedView", "Cafe object is valid, proceeding...")

        // Extract data from cafe object
        val name = receivedCafe.name
        val suburb = receivedCafe.suburb
        val description = receivedCafe.description

        Log.d("ExplodedView", "Cafe name: $name")
        Log.d("ExplodedView", "Cafe suburb: $suburb")
        Log.d("ExplodedView", "Cafe description: $description")

        // Check if name is null
        if (name == null) {
            Log.e("ExplodedView", "Cafe name is null!")
            cafe_name.text = "Unknown Cafe"
        } else {
            cafe_name.text = name
        }

        // Display suburb and description
        cafe_suburb.text = suburb.toString() ?: "Unknown Suburb"
        cafe_description.text = description ?: "No description available"

        Log.d("ExplodedView", "Text fields set successfully")

        // Display photo
        val cafeName = name.lowercase()
        Log.d("ExplodedView", "Looking for image for: $cafeName")

        when(cafeName) {
            "hinata cafe" -> cafe_image.setImageResource(R.drawable.hinata)
            "kith eatery" -> cafe_image.setImageResource(R.drawable.kith)
            "sinnamon" -> cafe_image.setImageResource(R.drawable.sinnamon)
            "tiamo cafe" -> cafe_image.setImageResource(R.drawable.tiamo)
            "boubar" -> cafe_image.setImageResource(R.drawable.boubar)
            "rustic spoon cafe" -> cafe_image.setImageResource(R.drawable.rustic)
            "gesha cafe" -> cafe_image.setImageResource(R.drawable.gesha)
            "cheol's cafe" -> cafe_image.setImageResource(R.drawable.cheol)
            "cafe guilty pleasure" -> cafe_image.setImageResource(R.drawable.guilty)
            "secondeli cafe" -> cafe_image.setImageResource(R.drawable.secondeli)
            "common grounds" -> cafe_image.setImageResource(R.drawable.common)
            "basement cafe" -> cafe_image.setImageResource(R.drawable.basement)
            else -> {
                Log.d("ExplodedView", "No matching image found for: $cafeName, using default")
                cafe_image.setImageResource(R.drawable.basement)
            }
        }

        Log.d("ExplodedView", "Image set successfully")

        // Favourite VM and favourite list
        val favouriteVM : FavouriteVM by viewModels()

        // Function to update button colour
        fun updateFavoriteButton(isFavorite: Boolean) {
            if (isFavorite) {
                favourite_button.setBackgroundColor(Color.parseColor("#EFBF04"))
                favourite_button.text = "★ Faved"
                favourite_button.setTextColor(Color.WHITE)
            } else {
                favourite_button.setBackgroundColor(Color.parseColor("#ff5a595b"))
                favourite_button.text = "☆ Fave"
                favourite_button.setTextColor(Color.BLACK)
            }
        }

        favouriteVM.favourite_set.observe(this) { favoritesList ->
            val isFavorite = favoritesList?.contains(name) == true
            updateFavoriteButton(isFavorite)
            Log.d("ExplodedView", "Observer triggered - Favorites updated, button state: $isFavorite")
        }

        if (receievedFavourite.isNotEmpty()) {
            favouriteVM.set_favourites(receievedFavourite?:ArrayList<String>())
        }

        favouriteVM.favourite_set.observe(this) { favoritesList ->
            val isFavorite = favoritesList?.contains(name) == true
            updateFavoriteButton(isFavorite)
        }

        // Update colour based on VM
        val isFavorite = favouriteVM.favourite_set.value?.contains(name) == true
        updateFavoriteButton(isFavorite)

        // Favourite button
        favourite_button.setOnClickListener {
            val currentFavorites = favouriteVM.favourite_set.value ?: ArrayList()
            val isCurrentlyFavourited = currentFavorites.contains(name)

            if (isCurrentlyFavourited) {
                favouriteVM.setFavourite(receivedCafe, false)
            } else {
                favouriteVM.setFavourite(receivedCafe, true)
            }
        }

        // Back button
        back_button.setOnClickListener {
            Log.d("ExplodedView", "Back button clicked")

            val intent = Intent(this, MainActivity::class.java)
            intent.putStringArrayListExtra("favourites", favouriteVM.favourite_set.value)
            intent.putExtra("cafe", receivedCafe)
            startActivity(intent)
        }

        intent.extras?.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val favouriteVM : FavouriteVM by viewModels()
        outState.putStringArrayList("current_favourites", favouriteVM.favourite_set.value)
    }
}