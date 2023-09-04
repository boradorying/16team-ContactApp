package com.example.contacts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2 (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ContactFragment()
            1 -> MyPageFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getItemCount(): Int {
        return 2 // 페이지 수
    }
}