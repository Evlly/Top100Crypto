package com.example.top100crypto.rest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface CoinGeckoApi{
    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ): Observable<List<GeckoCoin>>

    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
        @Path("id") id: String?,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "max"
    ): Observable<GeckoCoinChart>
}


data class GeckoCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Float,
    val market_cap: Float,
    val market_cap_rank: Int,
    val total_volume: Float,
    val price_change_percentage_24h: Float,
    val market_cap_change_percentage_24h: Float,
    val circulating_supply: Double,
    val total_supply: Float,
    val ath: Float,
    val ath_change_percentage: Float
)



data class GeckoCoinChart (
    var prices: List<List<Float>>
)