package com.zcf.app2.rel

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.zcf.app2.R
import com.zcf.app2.fragment.KnowledgeFragment
import com.zcf.app2.fragment.MainFragment
import com.zcf.app2.fragment.ProjectFragment
import com.zcf.app2.pager.ViewPageAdapger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_drawable.*


class MainActivity : AppCompatActivity() {
    private var time: Long = 0

    var listap: ListAdapter? = null

    var list = mutableListOf<Fragment>()

    var strList = mutableListOf<String>()

    var instance: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_drawable)
        instance = this
        setSupportActionBar(toobar)
        supportActionBar?.title = "首页"

        //侧滑菜单按钮
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toobar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //侧滑菜单的监听
        nav_view.setNavigationItemSelectedListener(mNavigationItemSelectedListener)
        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener)
        list.add(MainFragment())
        list.add(KnowledgeFragment())
        list.add(ProjectFragment())


        var viewPager = ViewPageAdapger(supportFragmentManager, list)

        main_pager.adapter = viewPager
        main_pager.currentItem = 0


    }

    //侧滑菜单的监听
    private val mNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.item_coll -> {

            }

            R.id.item_modify -> {

            }


            R.id.item_link -> {

            }
            R.id.item_exit -> {

            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        true
    }


    private val mOnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {//首页
                toobar.title = "首页"
                main_pager.currentItem = 0

                addfragment(MainFragment())


                return@OnNavigationItemSelectedListener true//不这样返回无法设置选中状态
            }

            R.id.navigation_dashboard -> {//知识体系

                toobar.title = "知识体系"
                main_pager.currentItem = 1
                return@OnNavigationItemSelectedListener true

            }


            R.id.navigation_notifications -> {//项目
                toobar.title = "项目"
                main_pager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    private fun addfragment(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        if (!supportFragmentManager.fragments.contains(fragment)) {
            transaction.add(R.id.main_fragment, fragment).commit()
        } else {




        }


    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (System.currentTimeMillis() - time < 2000) {
                super.onBackPressed()
            } else {
                Toast.makeText(this@MainActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }
        }


    }

}
