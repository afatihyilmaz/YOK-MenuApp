package com.example.deneme3.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import com.example.deneme3.model.MenuFeatures
import com.example.deneme3.model.RecyclerDateModel
import kotlinx.android.synthetic.main.item_active_date.view.*

class DateAdapter(): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    lateinit var dateList : ArrayList<RecyclerDateModel>
    lateinit var listener : OnItemClickListener
    lateinit var menuFeatures: List<MenuFeatures>
    var selectedPosition : Int?=null

    constructor(dateList: ArrayList<RecyclerDateModel>, listener: OnItemClickListener, menuFeatures: List<MenuFeatures>) : this(){
        this.dateList = dateList
        this.listener = listener
        this.menuFeatures = menuFeatures
    }

    inner class DateViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var recyclerDateNumberText : TextView?=null
        var recyclerDateMonthText : TextView?=null
        var recyclerDateDayText : TextView?=null

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
        holder.view.TVActiveNumberItem.text = dateList[position].dateNumber
        holder.view.TVActiveDateMonthItem.text = dateList[position].dateMonthName
        holder.view.TVActiveDateDayItem.text = dateList[position].dateDayName

        val layout : LinearLayout = holder.view.RVdateLayout
        layout.setBackgroundResource(R.color.grey)

        val isExpanded = dateList.get(position).isSelected
        if(isExpanded){
            layout.setBackgroundResource(R.color.pink)
        }else{
            layout.setBackgroundResource(R.color.grey)
        }

        holder.itemView.setOnClickListener {
            for(i in dateList){
                i.isSelected=false
            }
            val recyclerDate : RecyclerDateModel = dateList.get(position)
            recyclerDate.isSelected = !isExpanded
            layout.setBackgroundResource(R.color.pink)

            listener.onItemClick(position)
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