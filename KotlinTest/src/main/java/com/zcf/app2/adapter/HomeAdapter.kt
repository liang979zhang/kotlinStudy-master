package com.zcf.app2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zcf.app2.R
import com.zcf.app2.bean.Datas
import face.com.zdl.cctools.TimeUtils
import kotlinx.android.synthetic.main.item_view_adapter_home.view.*

/**
 * Created by Administrator on 2018/6/6.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.Holder> {

    private var context: Context? = null

    private var datas: List<Datas>? = null

    constructor(context: Context) {
        this.context = context

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_view_adapter_home, parent, false))
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas!!.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder!!.itemView.tv_author.text = datas!![position].author

        var time = TimeUtils.getFriendlyTimeSpanByNow(datas!![position].publishTime)
        holder.itemView.tv_time.text = time

        holder.itemView.tv_title.text = datas!![position].title
        holder.itemView.tv_type.text = datas!![position].superChapterName+"/"+datas!![position].chapterName

        if (datas!![position].collect) {
            Glide.with(context).load(R.mipmap.loveblue).into(holder.itemView.iv_coll)
        } else {
            Glide.with(context).load(R.mipmap.love).into(holder.itemView.iv_coll)
        }


    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun setdata(datas: List<Datas>) {
        this.datas = datas
        notifyDataSetChanged()

    }
}