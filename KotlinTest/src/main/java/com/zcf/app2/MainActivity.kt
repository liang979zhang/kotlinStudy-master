package com.zcf.app2

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.widget.Toast
import com.zcf.app2.fragment.KnowledgeFragment
import com.zcf.app2.fragment.MainFragment
import com.zcf.app2.fragment.NavigationFragment
import com.zcf.app2.fragment.ProjectFragment
import com.zcf.app2.rel.ListAdapter
import com.zcf.app2.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_drawable.*


class MainActivity : BaseActivity() {
    private var time: Long = 0

    var listap: ListAdapter? = null

    var list = mutableListOf<Fragment>()

    var strList = mutableListOf<String>()

    var instance: MainActivity? = null

    lateinit var mainfragment: MainFragment


    var knowledgefragment: KnowledgeFragment? = null
    var projectfragment: ProjectFragment? = null
    var navigationfragment: NavigationFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_drawable)
        instance = this
        setSupportActionBar(toobar)
        supportActionBar?.title = "首页"
        BottomNavigationViewHelper.disableShiftMode(main_navigation)
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


//        var viewPager = ViewPageAdapger(supportFragmentManager, list)
//
//        main_pager.adapter = viewPager
//        main_pager.currentItem = 0
        mainfragment = MainFragment()


        addFragment(mainfragment)


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
            R.id.navigation_home -> {
                toobar.title = "首页"
//                if (mainfragment == null) {
//                    mainfragment = MainFragment()
//                }
                addFragment(mainfragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                toobar.title = "知识体系"
                if (knowledgefragment == null) {
                    knowledgefragment = KnowledgeFragment()
                }
                addFragment(knowledgefragment!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                toobar.title = "项目"

                if (projectfragment == null) {
                    projectfragment = ProjectFragment()
                }
                addFragment(projectfragment!!)


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_navigation -> {
                toobar.title = "导航"
                if (navigationfragment == null) {
                    navigationfragment = NavigationFragment()
                }
                addFragment(navigationfragment!!)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    private fun addFragment(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        if (!supportFragmentManager.fragments.contains(fragment)) {//管理器没有此fragment  添加并显示

            transaction.add(R.id.main_fragment, fragment).commit()
        }

        var transaction2 = supportFragmentManager.beginTransaction()
        for (fra in supportFragmentManager.fragments) {
            if (fra.equals(fragment)) {
                transaction2.show(fragment)
            } else {
                transaction2.hide(fra)
            }
        }
        transaction2.commit()


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

}
