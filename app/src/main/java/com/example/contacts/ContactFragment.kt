package com.example.contacts

import ContactAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private val contactItems: MutableList<Contact> = mutableListOf()
    private lateinit var contactAdapter: ContactAdapter
    private var isGridMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        // 초기에 어댑터를 생성하고 RecyclerView에 설정
        contactAdapter = ContactAdapter(contactItems, isGridMode)
        binding.RVArea.adapter = contactAdapter
        setLayoutManager() // 초기 레이아웃 매니저 설정

        binding.gridBtn.setOnClickListener {
            isGridMode = true
            setLayoutManager()
        }

        binding.listBtn.setOnClickListener {
            isGridMode = false
            setLayoutManager()
        }

        contactItems.clear()
        contactItems.addAll(ContactsManager.contactsList)

        return binding.root
    }

    private fun setLayoutManager() {
        if (isGridMode) {
            val layoutManager = GridLayoutManager(requireContext(), 3)
            binding.RVArea.layoutManager = layoutManager
        } else {
            val layoutManager = LinearLayoutManager(requireContext())
            binding.RVArea.layoutManager = layoutManager
        }
        contactAdapter = ContactAdapter(contactItems, isGridMode) // 어댑터 다시설정!!!!!!!!!
        binding.RVArea.adapter = contactAdapter // 어댑터를 다시 설정해주는건 버튼을 눌렀을때 어댑터가 그냥 그리드뷰로 바뀌기 때문에 초기화해주기
        contactAdapter.notifyDataSetChanged()
    }

}

