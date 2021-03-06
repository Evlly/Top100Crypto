package com.example.top100crypto.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.example.top100crypto.R
import com.example.top100crypto.dateToString
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

@SuppressLint("ViewConstructor")
class MyMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {
    private val tvContent: TextView

    init {
        tvContent = findViewById(R.id.tvContent)
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tvContent.text = e?.y?.toString() + "\n" + e?.x?.dateToString("MMM dd, yyy")
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width/2)).toFloat(), (-height).toFloat())
    }

}