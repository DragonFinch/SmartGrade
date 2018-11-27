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

class AtmosphereAdapter(private val context: Context, private val list: List<String>) : RecyclerView.Adapter<AtmosphereAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_right_atmo, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val bean = list[position]
        if (bean != null) {
            holder.textName.text = bean
        }
        when (position) {
            0 -> {
                holder.textName.setTextColor(Color.parseColor("#666666"))
                holder.textName.setBackgroundColor(Color.parseColor("#00000000"))
            }
            1 -> {
                holder.textName.setTextColor(Color.parseColor("#ffffff"))
                holder.textName.setBackgroundResource(R.drawable.shape_rectangle_blue)
            }
            2 -> {
                holder.textName.setTextColor(Color.parseColor("#ffffff"))
                holder.textName.setBackgroundResource(R.drawable.shape_rectangle_orange)
            }
            3 -> {
                holder.textName.setTextColor(Color.parseColor("#ffffff"))
                holder.textName.setBackgroundResource(R.drawable.shape_rectangle_green)
            }
            4 -> {
                holder.textName.setTextColor(Color.parseColor("#ffffff"))
                holder.textName.setBackgroundResource(R.drawable.shape_rectangle_34c5da)
            }
            else -> {
                holder.textName.setTextColor(Color.parseColor("#ffffff"))
                holder.textName.setBackgroundResource(R.drawable.shape_rectangle_yellow)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: AppCompatTextView = itemView.findViewById(R.id.textItemRightAtmo)
    }
}
