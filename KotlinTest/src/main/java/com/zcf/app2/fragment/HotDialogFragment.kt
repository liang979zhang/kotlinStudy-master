package com.zcf.app2.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.*
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.zcf.app2.MainActivity
import com.zcf.app2.R
import com.zcf.app2.adapter.OfenAdapter
import com.zcf.app2.bean.OfenData
import com.zcf.app2.utils.HttpManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ofen_fragment_view.*


/**
 * Created by 89667 on 2018/6/7.
 */
class HotDialogFragment : DialogFragment() {
    var ofenData = mutableListOf<OfenData.Data>()

    private val ofenAdapter: OfenAdapter by lazy {
        OfenAdapter(activity, ofenData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.activity_DialogTransparent)
    }

    override fun onStart() {
        super.onStart()

        initDialog()
    }

    private fun initDialog() {
        val window = dialog.window
        val metrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.98).toInt() //DialogSearch的宽
//        window!!.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT)
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.TOP)
        window.setWindowAnimations(R.style.DialogEmptyAnimation)//取消过渡动画 , 使DialogSearch的出现更加平滑
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.ofen_fragment_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
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
            isEnableLoadMore = false
            autoRefresh()
            setOnRefreshListener {
                net()
                postDelayed({
                    run {
                        finishRefresh()
                    }
                }, 2000)

            }

        }

        net()

    }

    private fun initView() {

        back.setOnClickListener {
            dismiss()
        }

        tv_title.text = "常用网站"
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)



    }
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        dismiss()
//        return true
//
//    }
}