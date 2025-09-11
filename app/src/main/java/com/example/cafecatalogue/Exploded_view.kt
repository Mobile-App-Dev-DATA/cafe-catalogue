package com.example.cafecatalogue

import android.os.Bundle
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

        // Obtain cafe name from intent

        // From cafe name:
        // 1. Obtain location and opening hours
        // 2. Obtain cafe description
        // 3. Obtain pictures of cafe

        // 1. Obtain location and opening hours
        // 2. Obtain cafe description
        // 3. Obtain pictures of cafe

        // Display name, location, opening hours and 

    }
}