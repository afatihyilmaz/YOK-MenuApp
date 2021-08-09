package com.example.deneme3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deneme3.model.Menu
import com.example.deneme3.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Menu>> = MutableLiveData()

    fun getMonthlyMenu(){
        viewModelScope.launch {
            val response = repository.getMonthlyMenu()
            myResponse.value = response
        }
    }
}