package com.zcf.app2

import android.support.v7.widget.RecyclerView

/**
 * Created by Administrator on 2018/5/21.
 */
interface ItemClick {

    fun click(position: Int, holder: RecyclerView.ViewHolder)

}