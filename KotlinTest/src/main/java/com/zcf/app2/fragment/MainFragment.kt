package com.zcf.app2.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zcf.app2.R
import com.zcf.app2.adapter.BannerAdapter
import com.zcf.app2.bean.BanerBean
import com.zcf.app2.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_main_fragment.*

/**
 * Created by Administrator on 2018/6/5.
 */
class MainFragment : BaseFragment() {

    var bannerAdapter: BannerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.view_main_fragment, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        net()
        bannerAdapter = BannerAdapter(activity)
        recyclerview.adapter = bannerAdapter


        recyclerview.setScrollAnimationDuration(300)
        recyclerview.setOrientation(RecyclerView.VERTICAL)
        recyclerview.setVisibleChildCount(1)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (hidden) {
            net()
        }



    }

    private fun net() {

        HttpManager.manager.getServer().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ acticle: BanerBean ->
                    Log.i("acticle", acticle.data!!.size.toString())
                    bannerAdapter!!.setdata(acticle.data)
                }, { error ->
                    error.printStackTrace()
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }
}