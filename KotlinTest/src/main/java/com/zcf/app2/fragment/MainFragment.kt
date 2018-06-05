package com.zcf.app2.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.zcf.app2.R
import com.zcf.app2.adapter.BannerAdapter
import com.zcf.app2.adapter.BannerAdapter2
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
    var bannerAdapter2: BannerAdapter2? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.view_main_fragment, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        net()
        refreshLayout.autoRefresh()
        refreshLayout.autoLoadMore()
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {


            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                net()
            }


        })
        bannerAdapter = BannerAdapter(activity)
        bannerAdapter2 = BannerAdapter2(activity)
        recyclerview.adapter = bannerAdapter
        recyclerview2.adapter = bannerAdapter2

        recyclerview.setOrientation(RecyclerView.HORIZONTAL)
        recyclerview2.setOrientation(RecyclerView.VERTICAL)
        recyclerview2.setScrollAnimationDuration(300)
        recyclerview.setScrollAnimationDuration(300)
        recyclerview.setVisibleChildCount(1)
        recyclerview2.setVisibleChildCount(2)

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
                    bannerAdapter!!.setdata(acticle.data.subList(0, 3))
                    bannerAdapter2!!.setdata(acticle.data.subList(4, acticle.data.size))
                }, { error ->
                    error.printStackTrace()
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }
}