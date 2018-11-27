package com.school.nfcard.ui

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.nfcard.R
import com.school.nfcard.entity.SchoolInfo
/**
 * 此类的作用：娃娃的颜色列表适配器
 *
 *
 * Created by Liu on 2017/12/14.
 */

class SchoolAdapter(private val context: Context, private val list: List<SchoolInfo>) : RecyclerView.Adapter<SchoolAdapter.DollColorHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DollColorHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_doll_color, parent, false)
        return DollColorHolder(view)
    }

    override fun onBindViewHolder(holder: DollColorHolder, position: Int) {
        val bean = list[position]
        if (bean != null) {
            holder.textName.text = bean.name
            if (colorClickLister != null) {
                holder.textName.setOnClickListener {
                    if (colorClickLister != null) {
                        colorClickLister!!.onItemClick(bean)
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
        fun onItemClick(bean: SchoolInfo)
    }

    private var colorClickLister: OnItemDollColorClickLister? = null
    fun setColorClickLister(colorClickLister:OnItemDollColorClickLister) {
        this.colorClickLister = colorClickLister
    }
}
