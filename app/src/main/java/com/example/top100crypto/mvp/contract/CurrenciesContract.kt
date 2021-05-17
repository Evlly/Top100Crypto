package com.example.top100crypto.mvp.contract

import com.example.top100crypto.adapter.CurrenciesAdapter

class CurrenciesContract {
    interface View : BaseContract.View{
        fun addCurrency(currency: CurrenciesAdapter.Currency)
        fun notifyAdapter()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
    }

    abstract class Presenter: BaseContract.Presenter<View>(){
        abstract fun makeList()
        abstract fun refreshList()
    }
}