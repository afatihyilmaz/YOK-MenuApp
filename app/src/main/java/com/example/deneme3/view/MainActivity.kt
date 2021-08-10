package com.example.deneme3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deneme3.R


class MainActivity : AppCompatActivity() {

   // private lateinit var  viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.getMonthlyMenu()

        viewModel.myResponse.observe(this, Observer {response ->
            var textView = findViewById<TextView>(R.id.textView22)
            if (response.isSuccessful){
                textView.text = response.body()?.toString()
            }
            else{
                textView.text = "Error Error!!!!!!!!!"
            }
        })*/







    }




}