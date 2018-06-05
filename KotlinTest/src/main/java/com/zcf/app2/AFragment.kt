package com.zcf.app2

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_fragment.*

class AFragment : Fragment() {

//    var tv_1: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.view_fragment, container, false)

        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var aa = arguments.getString("aa")

        tv_1!!.text = aa
    }

    public fun newInstance(s: String): Fragment {
        var fragment = AFragment()
        var bundle = Bundle()
        bundle.putString("aa", s)
        fragment.arguments = bundle

        return fragment


    }


}