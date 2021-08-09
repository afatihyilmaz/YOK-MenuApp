package com.example.deneme3.repository

import com.example.deneme3.model.Menu
import com.example.deneme3.service.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMonthlyMenu(): Response<Menu> {
        return RetrofitInstance.api.getMonthlyMenu()
    }
}