package com.example.deneme3.viewmodel

import android.app.Application
import android.text.format.DateFormat
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.deneme3.adapter.DateAdapter
import com.example.deneme3.model.Menu
import com.example.deneme3.model.MenuFeatures
import com.example.deneme3.service.MenuAPIService
import com.example.deneme3.service.MenuDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
import java.util.*
import kotlin.collections.ArrayList


class MenuViewModel(application: Application) : BaseViewModel(application) {

    private val menuApiService = MenuAPIService()
    private val disposable = CompositeDisposable()

    val menus = MutableLiveData<Menu>()
    val menuError = MutableLiveData<Boolean>()
    val menuLoading = MutableLiveData<Boolean>()
    val menuFeatures = MutableLiveData<MenuFeatures>()

    val dateList = MutableLiveData<ArrayList<String>>()



     fun getDataFromAPI(){
        menuLoading.value = true

        disposable.add(
            menuApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Menu>(){
                    override fun onSuccess(t: Menu) {
                       // storeInDatabase(t)
                        val arr : List<MenuFeatures> = t.data
                        storeInDatabase(arr)
                        showDailyMenu(arr)
                     //   splitDate(arr)

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

    private fun showDailyMenu(menuFeature: List<MenuFeatures>){//Tarihi alıp öyle basmalıyız
        menuFeatures.value = menuFeature.get(0)
        menuError.value = false
        menuLoading.value = false
    }

    fun getDataFromRoom(){
        menuLoading.value = true
        launch {
            val menuFeatures = MenuDatabase(getApplication()).menuDao().getAllMenus()
            showDailyMenu(menuFeatures)
          //  splitDate(menuFeatures)
        }
        Toast.makeText(getApplication(), "Menus From Room", Toast.LENGTH_LONG).show()
    }

    private fun storeInDatabase(list: List<MenuFeatures>){
        launch {
            val dao = MenuDatabase(getApplication()).menuDao()
            dao.deleteAllMenuFeatures()
            dao.insertAllMenuFeatures(*list.toTypedArray())
          //  val listLongFood = dao.insertAllMenuFeatures(*list.toTypedArray())
            //Toast.makeText(getApplication(), "Menus from Room", Toast.LENGTH_LONG).show()
        }
    }

  /*  private fun splitDate(list: List<MenuFeatures>){
        //"2021-08-02T00:00:00",
        var i = 0

        while(i<list.size){
            var tempDate = list[i].menuDate
            var date = tempDate.subSequence(8,10).toString()

           dList.add(date)
            i +=1
        }



        println(dList)
        println("------")
        dateList.value = dList
        println(dateList.value.toString())

      //  var date = Date().toString() //Fri Aug 13 11:22:31 GMT 2021
    //    println(date)
    }*/

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}