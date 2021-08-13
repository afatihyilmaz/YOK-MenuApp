package com.example.deneme3.model

import androidx.room.*
import java.lang.reflect.Array

@Entity(tableName = "menu_features")
data class MenuFeatures(
    @PrimaryKey
    @ColumnInfo(name = "mfId")
    val id : Int,
    @ColumnInfo(name = "menuDate")
    val menuDate: String, //2021-08-03T00:00:00 -- formatında olmalı
    @ColumnInfo(name = "calorie")
    val calorie: Int,
    @ColumnInfo(name = "rate")
    val rate: String,
    @ColumnInfo(name = "rateCount")
    val rateCount: Int,
    @ColumnInfo(name = "foods")
    val foods: List<Food>?
)


