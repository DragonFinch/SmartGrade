package com.school.nfcard.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.nfcard.R

/**
 * 此类的作用：娃娃的颜色列表适配器
 *
 *
 * Created by Liu on 2017/12/14.
 */

class MottoAdapter(private val context: Context, private val list: List<String>) : RecyclerView.Adapter<MottoAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_right_motto, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val bean = list[position]
        if (bean != null) {
            holder.textName.text = bean
        }
        when (position) {
            0 -> {
                holder.textName.setTextColor(Color.parseColor("#8C8C8C"))
            }
            1 -> {
                holder.textName.setTextColor(Color.parseColor("#3DA6FF"))
            }
            2 -> {
                holder.textName.setTextColor(Color.parseColor("#F5784C"))
            }
            3 -> {
                holder.textName.setTextColor(Color.parseColor("#41D148"))
            }
            4 -> {
                holder.textName.setTextColor(Color.parseColor("#34C5DA"))
            }
            else -> {
                holder.textName.setTextColor(Color.parseColor("#FC8E2C"))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: AppCompatTextView = itemView.findViewById(R.id.textItemRightMotto)
    }
}
