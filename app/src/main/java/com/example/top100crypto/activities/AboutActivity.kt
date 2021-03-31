package com.example.top100crypto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.top100crypto.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}