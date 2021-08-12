package com.example.deneme3.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.deneme3.model.Food
import com.example.deneme3.model.MenuFeatures


@Database(entities = [MenuFeatures::class], version = 1)
@TypeConverters(FoodConverter::class)
abstract class MenuDatabase : RoomDatabase() {

    abstract  fun menuDao(): MenuDao

    //Singleton
    companion object{

        @Volatile private var instance : MenuDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext, MenuDatabase::class.java, "menudatabase.db")
            .build()
    }

}