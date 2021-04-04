package com.example.top100crypto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.top100crypto.R
import com.example.top100crypto.databinding.RecyclerViewItemBinding


class CurrenciesAdapter: BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {

    private lateinit var binding: RecyclerViewItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {

        binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding.root, binding)
    }

    class CurrencyViewHolder(view: View, binding: RecyclerViewItemBinding): BaseAdapter.BaseViewHolder(view){
        private var binding: RecyclerViewItemBinding

        init {
            itemView.setOnClickListener{

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