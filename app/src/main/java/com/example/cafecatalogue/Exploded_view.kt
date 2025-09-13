package com.example.cafecatalogue

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // Get xml data
        val cafe_name = findViewById<TextView>(R.id.cafe_name)
        val cafe_suburb = findViewById<TextView>(R.id.cafe_suburb)
        val cafe_description = findViewById<TextView>(R.id.cafe_description)
        val cafe_image = findViewById<ImageView>(R.id.cafe_image)
        val favourite_button = findViewById<TextView>(R.id.favourite_button)
        val back_button = findViewById<TextView>(R.id.back_button)

        // Obtain cafe object
        val receivedCafe = intent.getParcelableExtra<Cafe>("cafe")
        // Favourite VM
        val favouriteVM : FavouriteVM by activityViewModels()

        // 1. Obtain cafe name
        val name = receivedCafe?.name
        // 2. Obtain cafe suburb
        val suburb = receivedCafe?.suburb
        // 3. Obtain cafe description
        val description = receivedCafe?.description

        // Display name, suburb, description
        cafe_name.text = name
        cafe_suburb.text = suburb.toString() // Will this print only one or entire enum??????????
                                             // Should just print the one.  Suburb is an instance
                                             // of the suburb class ie one of the not the entire
                                             // enum values not the entire class
        cafe_description.text = description
/*
        // Display photo
        when(name?.lowercase())
        {
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
            "basement cafe" -> cafe_image.setImageResource(R.drawable.basement) //TODO Test
        }
*/
        /*
        // Favourite button
        favourite_button.setOnClickListener()
        {
            // Update favourite field in cafe!!!!!!!!!!!!!!!!!!!!!!!
            favouriteVM.observe(viewLifecycleOwner)
            {

            }
        }
        */

        // Back button
        back_button.setOnClickListener()
        {
            // Put cafe object in intent
            val intent = Intent(this, MainActivity::class.java)

            // Put favourite in intent

            // Start main activity
            startActivity(intent)
        }

    }
}