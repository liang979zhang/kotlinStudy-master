package com.zcf.app2.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zcf.app2.R
import com.zcf.app2.adapter.BannerAdapter
import com.zcf.app2.adapter.BannerAdapter2
import com.zcf.app2.adapter.HomeAdapter
import com.zcf.app2.bean.ArticleModel
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

    var homeAdapter: HomeAdapter? = null
    var page = -1

    var ismore = false
    private var datas = mutableListOf<ArticleModel.Data.Datas>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.view_main_fragment, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bannerAdapter = BannerAdapter(activity)
        bannerAdapter2 = BannerAdapter2(activity)
        recyclerview.adapter = bannerAdapter
        recyclerview2.adapter = bannerAdapter2
        rel_content.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rel_content.isNestedScrollingEnabled = false;

        homeAdapter = HomeAdapter(activity)

        rel_content.adapter = homeAdapter
        recyclerview.setOrientation(RecyclerView.HORIZONTAL)
        recyclerview2.setOrientation(RecyclerView.VERTICAL)
        recyclerview2.setScrollAnimationDuration(300)
        recyclerview.setScrollAnimationDuration(300)
        recyclerview.setVisibleChildCount(1)
        recyclerview2.setVisibleChildCount(2)

        refreshLayout.run {
            setRefreshHeader(ClassicsHeader(activity))
            setHeaderHeight(60f)
            isEnableRefresh = true
            isEnableLoadMore = true
            autoRefresh()

            setOnRefreshListener { _ ->
                net()
                page = -1
                ismore = false
                postDelayed({

                    refreshLayout.finishRefresh()

                }, 2000)
            }

            setOnLoadMoreListener { _ ->

                page++
                getContentList(page)
                ismore = true
                postDelayed({
                    refreshLayout.finishLoadMore()

                }, 2000)


            }


        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            net()
        } else {
            refreshLayout.finishRefresh()
            refreshLayout.finishLoadMore()
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
                    getContentList(page)
                }, { error ->
                    error.printStackTrace()
                }, {
                    Log.i("Kotlin", "onComplete")
                }, {
                    Log.i("Kotlin", "onStart")
                })
    }

    private fun getContentList(index: Int) {

        HttpManager.manager.getServer().homeArticle(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articlemodel: ArticleModel ->
                    if (articlemodel.errorCode == 0) {
                        page = articlemodel.data.curPage
                        if (ismore) {
                            datas.addAll(articlemodel.data.datas)
                        } else {
                            datas.clear()
                            datas.addAll(articlemodel.data.datas)
                        }
                        homeAdapter!!.setdata(datas)
                    }


                })


    }
}