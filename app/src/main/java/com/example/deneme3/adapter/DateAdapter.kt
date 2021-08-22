package com.example.deneme3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.deneme3.R
import com.example.deneme3.model.RecyclerDateModel
import com.example.deneme3.util.PlaceholderProgressBar
import com.example.deneme3.util.downloadFromUrl
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import kotlinx.android.synthetic.main.item_active_date.view.*
import kotlinx.android.synthetic.main.item_date.view.*

class DateAdapter(val dateList: ArrayList<RecyclerDateModel>): RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

     class DateViewHolder(var view: View) : RecyclerView.ViewHolder(view){

        init {
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_active_date,parent, false)

        return DateViewHolder(view)
    }



    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.view.TVActiveDateDayItem.text = dateList[position].dateName
        holder.view.TVActiveNumberItem.text = dateList[position].dateNumber


        val layout : LinearLayout = holder.view.RVdateLayout
        layout.setBackgroundResource(R.color.grey)





        val isExpanded = dateList.get(position).isSelected
        if(isExpanded){
           // holder.view.expendableLayout.visibility = View.VISIBLE
            layout.setBackgroundResource(R.color.pink)
        }else{
           // holder.view.expendableLayout.visibility = View.GONE
            layout.setBackgroundResource(R.color.grey)
        }



        holder.itemView.setOnClickListener {
            for(i in dateList){
                i.isSelected=false
            }
            val recyclerDate : RecyclerDateModel = dateList.get(position)
            recyclerDate.isSelected = !isExpanded
            layout.setBackgroundResource(R.color.pink)
            notifyDataSetChanged()
            Toast.makeText(holder.itemView.context, "Clicked on $position", Toast.LENGTH_SHORT).show()
        }


           /* if(dateList[position].isSelected){
                holder.view.expendableLayout.visibility = View.GONE
                holder.view.setBackgroundResource(R.drawable.date_background)
                dateList[position].isSelected = false
                notifyItemChanged(position)
            }
            else{
                holder.view.expendableLayout.visibility = View.VISIBLE
                holder.view.setBackgroundResource(R.drawable.active_date_background)
                dateList[position].isSelected = true
                notifyItemChanged(position)
            }*/

    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}