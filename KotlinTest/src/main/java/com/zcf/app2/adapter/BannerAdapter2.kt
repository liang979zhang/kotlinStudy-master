package com.zcf.app2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zcf.app2.R
import com.zcf.app2.bean.BanerBean
import kotlinx.android.synthetic.main.view_banner_item.view.*

/**
 * Created by Administrator on 2018/6/5.
 */
class BannerAdapter2 : RecyclerView.Adapter<BannerAdapter2.Holder> {

    var context: Context? = null

    var data: List<BanerBean.Data>? = null

    constructor(context: Context) {
        this.context = context


    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent!!.context).inflate(R.layout.view_banner_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {

        Glide.with(context).load(data!!.get(position).imagePath).into(holder!!.itemView.iv_icon)


    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    fun setdata(data: List<BanerBean.Data>) {
        this.data = data
        notifyDataSetChanged()
    }
}