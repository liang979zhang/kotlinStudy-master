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

    var strList = mutableListOf<String>()

    var instance: MainActivity? = null

    lateinit var mainPageAdapger: MainPageAdapger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        instance = this

        strList.add("viewpager测试")
        for (it in 1..10) {
            strList.add("aaaa$it aaaaa")
        }



        rel.layoutManager = LinearLayoutManager(this)
//        listap = ListAdapter(this, Click())
        listap = ListAdapter(this, object : ItemClick {
            override fun click(position: Int, holder: RecyclerView.ViewHolder) {

            }
        })
        rel.adapter = listap

        listap!!.setData(strList)
    }


    inner class Click() : ItemClick {
        override fun click(position: Int, holder: RecyclerView.ViewHolder) {
            when (position) {
                -11 -> {
                    Toast.makeText(this@MainActivity, "aaa${holder.adapterPosition}", Toast.LENGTH_SHORT).show()

                    when (holder.adapterPosition) {
                        1 -> {

                        }
                    }


                }
                -12 -> {


                }
            }

        }

    }
}
