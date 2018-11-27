package com.school.nfcard.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.nfcard.R
import com.school.nfcard.entity.Dynamics

/**
 * 此类的作用：娃娃的颜色列表适配器
 *
 *
 * Created by Liu on 2017/12/14.
 */

class DynamicsAdapter(private val context: Context, private val list: List<Dynamics>) : RecyclerView.Adapter<DynamicsAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_right_dyna, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val bean = list[position]
        if (bean != null) {
            holder.textName.text = "${position + 1}.${bean.class_content}"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: AppCompatTextView = itemView.findViewById(R.id.textItemRightDyna)
    }
}
