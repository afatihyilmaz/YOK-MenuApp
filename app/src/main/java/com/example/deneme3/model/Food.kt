package com.example.deneme3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "food")
data class Food(
   // @PrimaryKey
 //   @ColumnInfo(name= "foodId")
    val id : Int,
//    @ColumnInfo(name= "menuId")
//    val menuId : Int,
  //  @ColumnInfo(name = "name")
    val name : String,
 //   @ColumnInfo(name = "photoUrl")
    val photoUrl: String
)
