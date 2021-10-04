package com.test.animation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.animation.R
import com.test.animation.ui.adapters.ViewPagerAdapter
import com.test.animation.home
import com.test.animation.rank

class MainActivity : AppCompatActivity() {
   lateinit var html :String
    lateinit var drawer:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var list:MutableList<Fragment> = mutableListOf()
        var Tabs:TabLayout=findViewById(R.id.tl_tabs)
        var viewpager: ViewPager2 =findViewById(R.id.viewpager)
        drawer=findViewById(R.id.maindraw)
        val toolbar:Toolbar=findViewById(R.id.toomain)

        setSupportActionBar(toolbar)




            var home_fragment = home()
            var rank_fragment= rank()
            var comic_fragment=comic()
            list.add(home_fragment)
            list.add(rank_fragment)
             list.add(comic_fragment)
         var tabarry= arrayOf("今日推荐","一周排行","本子推荐")
        var pagerAdapter= ViewPagerAdapter(list,this)
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(Tabs,viewpager){
            tab,pos->tab.text=tabarry[pos]
        }.attach()





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home->drawer.openDrawer(Gravity.START)
        }


        return true
    }




}