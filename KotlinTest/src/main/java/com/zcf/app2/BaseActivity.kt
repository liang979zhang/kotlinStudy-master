package com.zcf.app2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Administrator on 2018/5/21.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    fun Context.toast(message:CharSequence)= Toast.makeText(this,message, Toast.LENGTH_LONG).show()


}