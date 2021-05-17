package com.example.top100crypto.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.top100crypto.R
import com.example.top100crypto.chart.LatestChart
import com.example.top100crypto.databinding.ActivityChartBinding
import com.example.top100crypto.di.App
import com.example.top100crypto.mvp.contract.LatestChartContract
import com.example.top100crypto.mvp.presenter.LatestChartPresenter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import java.text.DecimalFormat
import javax.inject.Inject

class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener, LatestChartContract.View {

    @Inject
    lateinit var latestChart: LatestChart

    @Inject
    lateinit var presenter: LatestChartPresenter

    lateinit var binding: ActivityChartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.appComponent.inject(this)
        presenter.attach(this)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val marketCapRank = intent.getIntExtra("marketCapRank", 0)
        val symbol = intent.getStringExtra("symbol")
        val marketCap = intent.getStringExtra("marketCap")
        val marketCapChangePercentage24h = intent?.getFloatExtra("marketCapChangePercentage24h", 0.0f)
        val priceChangePercentage24h = intent?.getFloatExtra("priceChangePercentage24h", 0.0f)
        val totalVolume = intent?.getFloatExtra("totalVolume", 0.0f)
        val ath = intent?.getFloatExtra("ath", 0.0f)
        val athChangePercentage = intent?.getFloatExtra("athChangePercentage", 0.0f)
        val circulatingSupply = intent?.getDoubleExtra("circulatingSupply", 0.0)
        val totalSupply = intent?.getLongExtra("totalSupply", 0)
        val image = intent.getStringExtra("image")

        Glide.with(this).load(image).into(binding.ivCurrencyDetailIcon)

        supportActionBar?.title = name

        val df = DecimalFormat("#")
        df.maximumFractionDigits = 2

        binding.tvDetailMarketCapRank.text = marketCapRank.toString()
        binding.tvMarketCapChange.text = marketCapChangePercentage24h.toString()
        binding.tvATH.text = ath.toString()
        binding.tvAthChange.text = df.format(athChangePercentage)
        binding.tvCirculatingSupply.text = df.format(circulatingSupply)
        binding.tvTotalSupply.text = totalSupply.toString()

        val idd = intent.getStringExtra("id") as String

        //Log.d("IDterter", idd)

        presenter.makeChart(idd)

        latestChart.initChart(binding.chartCurrency)
    }



    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(date: Float, value: Float) {

        latestChart.addEntry(value, date)
    }

    override fun addEntryToChart(value: Float, date: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        binding.progressChart.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressChart.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}