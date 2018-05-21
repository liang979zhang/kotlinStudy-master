package com.zcf.app2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Administrator on 2018/5/21.
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.Holder> {
    var strList: MutableList<String>? = null

    var context: Context? = null
    var click: ItemClick? = null

    constructor(context: Context, click: ItemClick) {
        this.context = context
        this.click = click
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return strList!!.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder!!.name.text = strList!!.get(position)


        holder.name.setOnClickListener({
            click!!.click(-11, holder)
//            Toast.makeText(context,"aaa",Toast.LENGTH_SHORT).show()

        })
    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView!!.findViewById(R.id.tv_name)


    }

    fun setData(strList: MutableList<String>) {
        this.strList = strList
        notifyDataSetChanged()
    }
}