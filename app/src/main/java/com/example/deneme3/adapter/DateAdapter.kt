package com.example.deneme3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import kotlinx.android.synthetic.main.item_active_date.view.*
import kotlinx.android.synthetic.main.item_date.view.*

class DateAdapter(val dateList: ArrayList<String>): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {


    class DateViewHolder(var view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_active_date,parent, false)
        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.view.TVActiveDateDayItem.text = "DAY"
        holder.view.TVActiveNumberItem.text = dateList[position]
    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}