package com.example.deneme3.viewmodel

import android.app.Application
import android.text.format.DateFormat
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.deneme3.adapter.DateAdapter
import com.example.deneme3.model.Menu
import com.example.deneme3.model.MenuFeatures
import com.example.deneme3.model.RecyclerDateModel
import com.example.deneme3.service.MenuAPIService
import com.example.deneme3.service.MenuDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.launch
import okhttp3.internal.http.toHttpDateString
import org.intellij.lang.annotations.Language
import java.util.*
import kotlin.collections.ArrayList



class MenuViewModel(application: Application) : BaseViewModel(application) {

    private val menuApiService = MenuAPIService()
    private val disposable = CompositeDisposable()

    var menus = MutableLiveData<Menu>()
    val menuError = MutableLiveData<Boolean>()
    val menuLoading = MutableLiveData<Boolean>()
    val menuFeatures = MutableLiveData<List<MenuFeatures>>()
    var dateRecyclerList : ArrayList<RecyclerDateModel> = arrayListOf()



     fun getDataFromAPI(): Int{
        menuLoading.value = true
         var pos = 0

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
                        pos = splitDate(arr)


                        Toast.makeText(getApplication(),"Menus From API", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        menuLoading.value = false
                        menuError.value = true
                        e.printStackTrace()
                    }

                })
        )
         println("getapi " + pos)
         return pos
    }

    fun showCurrentDay(menuFeature: List<MenuFeatures>): Int{
        val currentDay = Calendar.getInstance().time.date
        var i = 0
        while (i < menuFeature.size){

            if(currentDay == menuFeature[i].dateDay.toInt()){
                println("show current " + i)
                return i
            }
            i+=1
        }

    return 0
    }


    fun showDailyMenu(menuFeature: List<MenuFeatures>){
        menuFeatures.value = menuFeature
        menuError.value = false
        menuLoading.value = false
    }

    fun getDataFromRoom(){
        menuLoading.value = true
        launch {
            val menuFeatures = MenuDatabase(getApplication()).menuDao().getAllMenus()
            //showDailyMenu(menuFeatures)
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

    fun splitDate(list: List<MenuFeatures>): Int{
        //"2021-08-02T00:00:00",
        var i = 0
        var temp = 0
        val currentDate = Calendar.getInstance().time.date
        while(i < list.size){
            var recModel = RecyclerDateModel("DAY","00","MONTH",false)
            if(currentDate == list[i].dateDay.toInt()){
                recModel.dateNumber = list[i].dateDay
                recModel.dateDayName = list[i].dateDayName
                recModel.dateMonthName = list[i].dateMonthName
                recModel.isSelected = true
                dateRecyclerList.add(recModel)
                temp = i
            }else{
                recModel.dateNumber = list[i].dateDay
                recModel.dateDayName = list[i].dateDayName
                recModel.dateMonthName = list[i].dateMonthName
                recModel.isSelected = false
                dateRecyclerList.add(recModel)
            }

            println(recModel)
            i+=1
        }
        println("get split " + temp)
        return temp
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}