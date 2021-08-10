package com.example.deneme3.service

import com.example.deneme3.model.Menu
import io.reactivex.Single
import retrofit2.http.GET

interface MenuAPI {

    @GET("getMonthlyMenu")
    fun getMonthlyMenu():Single<Menu>
}