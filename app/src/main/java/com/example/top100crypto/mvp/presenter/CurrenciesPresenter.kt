package com.example.top100crypto.mvp.presenter

import com.example.top100crypto.adapter.CurrenciesAdapter
import com.example.top100crypto.di.App
import com.example.top100crypto.formatThousands
import com.example.top100crypto.mvp.contract.CurrenciesContract
import com.example.top100crypto.rest.CoinGeckoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class CurrenciesPresenter: CurrenciesContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeList() {
        view.showProgress()
        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it) }
            .doOnNext{
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }
            .doOnComplete { view.hideProgress() }
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            },{
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            }))
    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}