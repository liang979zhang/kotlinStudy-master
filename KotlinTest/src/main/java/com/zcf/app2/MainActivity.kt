package com.zcf.app2

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : BaseActivity() {

    var listap: ListAdapter? = null

    var list = mutableListOf<Fragment>()

    var mainpager :MainPageAdapger ?= null


    var instance: MainActivity? = null

    lateinit var mainPageAdapger: MainPageAdapger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        instance = this
        mainpager = MainPageAdapger(supportFragmentManager,list)
        list.add(AFragment().newInstance("aa"))
        list.add(AFragment().newInstance("bb"))
        list.add(AFragment().newInstance("vv"))
        viewpager.adapter =mainpager


    }



}
