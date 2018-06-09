package com.zcf.app2.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.zcf.app2.R
import com.zcf.app2.bean.OfenData
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import getRandomColor

/**
 * Created by 89667 on 2018/6/6.
 */
class OfenAdapter(context: Context, datas: List<OfenData.Data>) : TagAdapter<OfenData.Data>(datas) {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(parent: FlowLayout?, position: Int, data: OfenData.Data?): View {
        return (inflater!!.inflate(R.layout.text_view, parent, false) as TextView).apply {
            text = data!!.name


            val parseColor = try {
                Color.parseColor(getRandomColor())
            } catch (_: Exception) {
                @Suppress("DEPRECATION")
                context.resources.getColor(R.color.colorAccent)
            }
            setTextColor(parseColor)

        }


    }
}