package com.example.deneme3.adapter


import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import com.example.deneme3.model.MenuFeatures
import com.example.deneme3.model.RecyclerDateModel
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import kotlinx.android.synthetic.main.item_active_date.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
import kotlin.time.days

class DateAdapter(): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    lateinit var dateList : ArrayList<RecyclerDateModel>
    lateinit var listener : OnItemClickListener
    var currentDate : Int?=null
    var selectedPosition : Int?=null

    constructor(dateList: ArrayList<RecyclerDateModel>, listener: OnItemClickListener) : this(){
        this.dateList = dateList
        this.listener = listener
    }

    inner class DateViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var recyclerDateNumberText : TextView?=null
        var recyclerDateMonthText : TextView?=null
        var recyclerDateDayText : TextView?=null
        var recyclerView : RecyclerView?=null

        init {
            recyclerDateNumberText = view.TVActiveNumberItem
            recyclerDateMonthText = view.TVActiveDateMonthItem
            recyclerDateDayText = view.TVActiveDateDayItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_active_date,parent, false)

        return DateViewHolder(view)
    }



    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val layout : LinearLayout = holder.view.RVdateLayout


        holder.view.TVActiveNumberItem.text = dateList[position].dateNumber
        holder.view.TVActiveDateMonthItem.text = dateList[position].dateMonthName
        holder.view.TVActiveDateDayItem.text = dateList[position].dateDayName

       // layout.setBackgroundResource(R.color.pink)
       // layout.setBackgroundResource(R.color.grey)

        val isExpanded = dateList.get(position).isSelected
        if(isExpanded){
            layout.setBackgroundResource(R.color.pink)
            dateList.get(position).isSelected = true
        }else{
            layout.setBackgroundResource(R.color.grey)
            dateList.get(position).isSelected = false
        }

        holder.itemView.setOnClickListener {
            for(i in dateList){
                i.isSelected=false
            }
            val recyclerDate : RecyclerDateModel = dateList.get(position)
            recyclerDate.isSelected = !isExpanded
            layout.setBackgroundResource(R.color.pink)
            listener.onItemClick(position)
            println(Calendar.getInstance().time.hours)
            println(Calendar.getInstance().time.date)

            notifyDataSetChanged()

          //  Toast.makeText(holder.itemView.context, "$position Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}