package com.example.deneme3.model

import com.google.gson.annotations.SerializedName

data class Menu(
    //@SerializedName("count")
    val count : Int,
   // @SerializedName("data")
    val data : List<MenuFeatures>?,
   // @SerializedName("success")
    val success : Boolean,
   //@SerializedName("message")
    val message : String
)
