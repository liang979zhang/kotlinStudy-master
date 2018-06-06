package com.zcf.app2.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.zcf.app2.MainActivity
import com.zcf.app2.R
import com.zcf.app2.adapter.OfenAdapter
import com.zcf.app2.bean.BanerBean
import com.zcf.app2.bean.OfenData
import com.zcf.app2.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.ofen_fragment_view.*

/**
 *
 * 常用
 * Created by 89667 on 2018/6/6.
 */
class OfenFragment : Fragment() {

    var ofenData = mutableListOf<OfenData.Data>()

    private val ofenAdapter: OfenAdapter by lazy {
        OfenAdapter(activity, ofenData)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.ofen_fragment_view, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        id_flowlayout.apply {
            adapter = ofenAdapter
            setOnTagClickListener { view, position, parent ->

                if (ofenData.size != 0) {

                    Intent(activity, MainActivity::class.java).run {
                        startActivity(this)
                    }
                }

                true
            }
        }
        refreshLayout.apply {

            setRefreshHeader(BezierRadarHeader(activity))
            setHeaderHeight(60f)
            isEnableRefresh = true
            autoRefresh()
            setOnRefreshListener {
                net()
                postDelayed({
                    run {
                        finishRefresh()
                    }
                },2000)

            }

        }

        net()
    }


    private fun net() {
        HttpManager.manager.getServer().getFriendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: OfenData ->
                    ofenData.clear()
                    ofenData.addAll(data.data)
                    ofenAdapter.notifyDataChanged()
                    refreshLayout.finishRefresh()

                }, { error ->
                    error.printStackTrace()
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }

}