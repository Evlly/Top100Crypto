package com.example.top100crypto.di

import android.app.Application

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .mvpModule(MvpModule())
            .chartModule(ChartModule())
            .build()
    }
}