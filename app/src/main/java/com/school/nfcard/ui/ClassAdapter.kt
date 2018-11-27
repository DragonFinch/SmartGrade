package com.school.nfcard.ui

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.nfcard.R
import com.school.nfcard.entity.ClassInfo

/**
 * 此类的作用：娃娃的颜色列表适配器
 *
 *
 * Created by Liu on 2017/12/14.
 */

class ClassAdapter(private val context: Context, private val list: List<ClassInfo>) : RecyclerView.Adapter<ClassAdapter.DollColorHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DollColorHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_doll_color, parent, false)
        return DollColorHolder(view)
    }

    override fun onBindViewHolder(holder: DollColorHolder, position: Int) {
        val colorBean = list[position]
        if (colorBean != null) {
            holder.textName.text = colorBean.name
            if (colorClickLister != null) {
                holder.textName.setOnClickListener {
                    if (colorClickLister != null) {
                        colorClickLister!!.onItemClick(colorBean)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DollColorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: AppCompatTextView = itemView.findViewById(R.id.text_item_doll_color)
    }


    /***
     * 选中某个颜色的娃娃接口回调
     */
    interface OnItemDollColorClickLister {
        fun onItemClick(colorBean: ClassInfo)
    }

    private var colorClickLister: OnItemDollColorClickLister? = null
    fun setColorClickLister(colorClickLister: OnItemDollColorClickLister) {
        this.colorClickLister = colorClickLister
    }
}
