package com.example.top100crypto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.top100crypto.R
import com.example.top100crypto.activities.ChartActivity
import com.example.top100crypto.databinding.RecyclerViewItemBinding


class CurrenciesAdapter: BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {

    private lateinit var binding: RecyclerViewItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {

        binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context))
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.root.layoutParams = lp
        return CurrencyViewHolder(binding.root, binding)
    }

    class CurrencyViewHolder(view: View, binding: RecyclerViewItemBinding): BaseAdapter.BaseViewHolder(view){
        private var binding: RecyclerViewItemBinding


        var id: String = ""
        var symbol: String = ""
        var name: String = ""
        var image: String = ""
        var marketCap: String = ""
        var marketCapRank: Int = 0
        var marketCapChangePercentage24h: Float = 0.0f
        var priceChangePercentage24h: Float = 0.0f
        var totalVolume: Float = 0.0f
        var ath: Float = 0.0f
        var athChangePercentage: Float = 0.0f
        var circulatingSupply: Double = 0.0
        var totalSupply: Float = 0.0f

        init {
            itemView.setOnClickListener{
                var intent = Intent(itemView.context, ChartActivity::class.java)
                intent.putExtra("id", id)
                    .putExtra("name", name)
                    .putExtra("symbol", symbol)
                    .putExtra("image", image)
                    .putExtra("marketCapRank", marketCapRank)
                    .putExtra("marketCap", marketCap)
                    .putExtra("marketCapChangePercentage24h", marketCapChangePercentage24h)
                    .putExtra("priceChangePercentage24h", priceChangePercentage24h)
                    .putExtra("totalVolume", totalVolume)
                    .putExtra("ath", ath)
                    .putExtra("athChangePercentage", athChangePercentage)
                    .putExtra("circulatingSupply", circulatingSupply)
                    .putExtra("totalSupply", totalSupply)
                itemView.context.startActivity(intent)
            }
            this.binding = binding
        }

        override fun bind(item: Any) {
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(binding.ivCurrencyIcon)
                binding.tvCurrencySym.text = item.symbol
                binding.tvCurrencyName.text = item.name
                binding.tvCurrencyMarketCap.text = item.marketCap
                binding.tvCurrencyPrice.text = item.price.toString()
                id = item.id
                symbol = item.symbol
                name = item.name
                image = item.image
                marketCapRank = item.marketCapRank
                marketCapChangePercentage24h = item.marketCapChangePercentage24h
                priceChangePercentage24h = item.priceChangePercentage24h
                totalVolume = item.totalVolume
                ath = item.ath
                athChangePercentage = item.athChangePercentage
                circulatingSupply = item.circulatingSupply
                totalSupply = item.totalSupply
            }
        }
    }

    data class Currency(
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Float,
        val ath: Float,
        val athChangePercentage: Float
    )

}