package com.xinzhidi.nfc.widget.timetable

import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.school.nfcard.R

/**
 *此类的作用：课程表的适配器
 *
 * Created by Liu on 2018/8/13.
 *
 */
class TimetableAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<TimetableAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_table, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.text.text = list[position]
        if (position <= 7) {
            holder.text.setTextColor(Color.parseColor("#3F51B5"))
        } else {
            holder.text.setTextColor(Color.parseColor("#666666"))
        }

        if (position % 8 == 0) {
            holder.text.textSize = 17f
        } else {
            holder.text.textSize = 24f
        }
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: AppCompatTextView = itemView.findViewById(R.id.textTimeTableName)
    }
}