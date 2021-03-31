package com.example.top100crypto.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.top100crypto.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class AboutActivity : AppCompatActivity() {

    lateinit var buttonRateApp: Button
    lateinit var  adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        buttonRateApp = findViewById<Button>(R.id.buttonRateApp)
        adView = findViewById<AdView>(R.id.adView)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonRateApp.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${applicationContext.packageName}")))
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}