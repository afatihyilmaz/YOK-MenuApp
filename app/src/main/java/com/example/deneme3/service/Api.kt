package com.example.deneme3.service

import com.example.deneme3.model.Menu
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("getMonthlyMenu")
    suspend fun getMonthlyMenu(): Response<Menu>
}