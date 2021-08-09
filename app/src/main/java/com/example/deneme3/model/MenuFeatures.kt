package com.example.deneme3.model


data class MenuFeatures(
    val id : Int,
    val menuDate: String, //2021-08-03T00:00:00 -- formatında olmalı
    val calorie: Int,
    val rate: String,
    val rateCount: Int,
    val foods: List<Food>?
)
