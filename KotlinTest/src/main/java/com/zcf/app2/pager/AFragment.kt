package com.zcf.app2.pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zcf.app2.R
import kotlinx.android.synthetic.main.view_fragment.*

class AFragment : Fragment() {
    var string: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.view_fragment, container, false)

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        string = arguments.getString("text")
        tv_1.text = string
        Log.e("tag", string!!)

        webview.setOnKeyListener { v, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_BACK) {

                return@setOnKeyListener  true
            }

            return@setOnKeyListener false
        }
    }


    /**
     * 静态工厂方法需要一个String型的值来初更新fragment中的内容，
     * 然后返回新的fragment到调用者
     */
    fun newInstance(text: String): Fragment {
        val args = Bundle()

        args.putString("text", text)
        val fragment = AFragment()
        fragment.arguments = args
        return fragment
    }

}