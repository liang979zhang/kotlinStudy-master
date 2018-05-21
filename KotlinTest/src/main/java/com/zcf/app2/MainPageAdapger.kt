package com.zcf.app2

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

class MainPageAdapger : FragmentPagerAdapter {


    var list: MutableList<Fragment>? = null

    lateinit var s: String

    constructor(fm: FragmentManager, list: MutableList<Fragment>) : super(fm) {
        this.list = list

    }

    override fun getItem(position: Int): Fragment {
        return list!![position]
    }

    override fun getCount(): Int {

        return list!!.size
    }

    fun Data(s: String) {
        this.s = s
        Log.e("tag", "s=====$s")

    }
}