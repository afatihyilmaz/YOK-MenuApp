package com.example.deneme3.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.deneme3.model.Menu
import com.example.deneme3.service.MenuAPIService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver


class MenuViewModel(application: Application) : BaseViewModel(application) {

    private val menuApiService = MenuAPIService()
    private val disposable = CompositeDisposable()

    val menus = MutableLiveData<Menu>()
    val menuError = MutableLiveData<Boolean>()
    val menuLoading = MutableLiveData<Boolean>()

     fun getDataFromAPI(){
        menuLoading.value = true

        disposable.add(
            menuApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Menu>(){
                    override fun onSuccess(t: Menu) {
                       // storeInDatabase(t)
                        showDailyMenu(t)
                        Toast.makeText(getApplication(),"Menus From API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        menuLoading.value = false
                        menuError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showDailyMenu(menu : Menu){
        menus.value = menu
        menuError.value = false
        menuLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}