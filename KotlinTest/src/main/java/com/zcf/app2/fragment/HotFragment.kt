package com.zcf.app2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zcf.app2.R

/**
 * Created by 89667 on 2018/6/6.
 */
class HotFragment :Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.ofen_fragment_view, container, false)
    }
}