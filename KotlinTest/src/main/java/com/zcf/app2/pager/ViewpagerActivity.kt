package com.zcf.app2.pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.zcf.app2.R
import kotlinx.android.synthetic.main.activity_viewpager.*

class ViewpagerActivity : AppCompatActivity() {


    var pagerAdapter: ViewPageAdapger? = null

    var fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)


        fragmentList.add(AFragment().newInstance("aa"))
        fragmentList.add(AFragment().newInstance("bb"))
        fragmentList.add(AFragment().newInstance("cc"))
        fragmentList.add(AFragment().newInstance("dd"))

        pagerAdapter = ViewPageAdapger(supportFragmentManager, fragmentList)
        viewpager.adapter = pagerAdapter

        alphaIndicator.setViewPager(viewpager)

        viewpager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

        })


//        viewpager.addon


    }
}
