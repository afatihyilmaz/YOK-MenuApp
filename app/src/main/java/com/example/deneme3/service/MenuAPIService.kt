package com.example.deneme3.service

import com.example.deneme3.model.Menu
import com.example.deneme3.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MenuAPIService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MenuAPI::class.java)

    fun getData() : Single<Menu>{
        return api.getMonthlyMenu()
    }
}