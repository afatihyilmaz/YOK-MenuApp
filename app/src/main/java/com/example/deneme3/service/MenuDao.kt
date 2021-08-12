package com.example.deneme3.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deneme3.model.Food
import com.example.deneme3.model.Menu
import com.example.deneme3.model.MenuFeatures

@Dao
interface MenuDao {

    @Insert
    suspend fun insertAllMenuFeatures(vararg menus: MenuFeatures)
    //Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple food objects
    // List<Long> -> primary keys


    @Query("SELECT * FROM menu_features")
    suspend fun getAllMenus() : List<MenuFeatures>

    //Tarih parametreli bir query yazılmalı. Hangi tarihi seçtiyse onu getirmeli

    @Query("DELETE FROM menu_features")
    suspend fun deleteAllMenuFeatures()

}