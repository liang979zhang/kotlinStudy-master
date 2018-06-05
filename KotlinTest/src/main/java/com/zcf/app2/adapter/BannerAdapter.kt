package com.zcf.app2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zcf.app2.R
import kotlinx.android.synthetic.main.view_banner_item.view.*

/**
 * Created by Administrator on 2018/6/5.
 */
class BannerAdapter : RecyclerView.Adapter<BannerAdapter.Holder> {

    var context: Context? = null

    constructor(context: Context) {
        this.context = context


    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent!!.context).inflate(R.layout.view_banner_item, parent, false))
    }

    override fun getItemCount(): Int {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        holder!!.itemView.iv_icon

//        Glide.with(context).load()
    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}