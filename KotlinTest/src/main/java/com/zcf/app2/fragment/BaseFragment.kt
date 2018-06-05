package com.zcf.app2.fragment

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Administrator on 2018/6/5.
 */
open class BaseFragment : Fragment() {

    fun  Fragment.tost(message:CharSequence) = Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
}