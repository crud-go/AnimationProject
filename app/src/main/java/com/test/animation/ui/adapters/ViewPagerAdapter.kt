package com.test.animation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


    class ViewPagerAdapter(var list: MutableList<Fragment>, fragment: FragmentActivity):
        FragmentStateAdapter(fragment) {
        override fun getItemCount():Int = list.size


        override fun createFragment(position: Int): Fragment {

            return list[position]
        }
}