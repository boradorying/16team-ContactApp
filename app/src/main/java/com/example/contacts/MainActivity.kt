package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contacts.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = ViewPager2(supportFragmentManager, lifecycle)
        binding.pager.adapter = viewPager2

        // TabLayout
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }
}