package com.example.deneme3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import com.example.deneme3.util.downloadFromUrl
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import kotlinx.android.synthetic.main.item_active_date.view.*
import kotlinx.android.synthetic.main.item_date.view.*

class DateAdapter(val dateList: ArrayList<String>): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

   /* private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }*/

     class DateViewHolder(var view: View) : RecyclerView.ViewHolder(view){


       /* init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_date,parent, false)

        return DateViewHolder(view)
    }


    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.view.TVItemDateDayAbbreviation.text = "DAY"
        holder.view.TVItemDateNumber.text = dateList[position]

        holder.itemView.setOnClickListener {
            holder.itemView.setBackgroundResource(R.drawable.active_date_background)
            Toast.makeText(holder.itemView.context, "Clicked on $position", Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}