package com.example.top100crypto.di

import com.example.top100crypto.activities.MainActivity
import com.example.top100crypto.fragments.CurrenciesListFragment
import com.example.top100crypto.mvp.presenter.CurrenciesPresenter
import com.example.top100crypto.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)

}