package com.example.deneme3.service

import androidx.room.TypeConverter
import com.example.deneme3.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodConverter {

    @TypeConverter
    fun fromFoodList(list: List<Food>?) : String{
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromString(value: String): List<Food>{
        return Gson().fromJson(value, Array<Food>::class.java).toList()
    }

}