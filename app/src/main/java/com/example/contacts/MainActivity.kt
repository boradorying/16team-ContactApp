package com.example.contacts
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.databinding.TabItemBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = ViewPager2(supportFragmentManager, lifecycle)
        binding.pager.adapter = viewPager2

        setupTabs()
        centerAlignTabs()
    }

    // TabLayout
    private fun setupTabs() {
        // Font 가져오기
        val typeface = ResourcesCompat.getFont(this, R.font.komi)

        // 탭 선택 리스너 설정
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            // 클릭 되었을 때
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tabBinding = TabItemBinding.bind(customView!!)
                when (tab?.position) {
                    0 -> tabBinding.tabIcon.setImageResource(R.drawable.clicked_contact)
                    1 -> tabBinding.tabIcon.setImageResource(R.drawable.clicked_user)
                }
                tabBinding.tabTxt.typeface = Typeface.create(typeface, Typeface.BOLD)
                tabBinding.tabTxt.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
            }

            // 클릭 안 되었을 때
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tabBinding = TabItemBinding.bind(customView!!)
                when (tab?.position) {
                    0 -> tabBinding.tabIcon.setImageResource(R.drawable.unclicked_contact)

                    1 -> tabBinding.tabIcon.setImageResource(R.drawable.unclicked_user)
                }
                tabBinding.tabTxt.typeface = Typeface.create(typeface, Typeface.NORMAL)
                tabBinding.tabTxt.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // 탭과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            val tabBinding = TabItemBinding.inflate(LayoutInflater.from(this))
            when (position) {
                0 -> {
                    tabBinding.tabIcon.setImageResource(R.drawable.unclicked_contact)
                    tabBinding.tabTxt.text = "CONTACT"
                }

                1 -> {
                    tabBinding.tabIcon.setImageResource(R.drawable.unclicked_user)
                    tabBinding.tabTxt.text = "MYPAGE"
                }

                else -> {
                    tabBinding.tabTxt.text = "Tab ${position + 1}"
                }
            }
            // Tab 디자인 적용
            tab.customView = tabBinding.root
        }.attach()

        // 초기 탭 선택
        binding.tabLayout.getTabAt(0)?.select()
    }

    // TabLayout에서 Tab 가운데 정렬 코드
    private fun centerAlignTabs() {
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val tabWidth = screenWidth / binding.tabLayout.tabCount
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            val tabView = tab?.view?.layoutParams as LinearLayout.LayoutParams
            tabView.weight = 1f
            tabView.width = tabWidth
            tab?.view?.requestLayout()
        }
    }
}
