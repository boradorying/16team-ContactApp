package com.example.contacts

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
    //text
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        contactAdapter = ContactAdapter(contactItems, isGridMode)
        setLayoutManager()

        binding.gridBtn.setOnClickListener {
            isGridMode = true
            setLayoutManager()
            contactAdapter.notifyDataSetChanged()
        }

        binding.listBtn.setOnClickListener {
            isGridMode = false
            setLayoutManager()
            contactAdapter.notifyDataSetChanged()
        }

        binding.RVArea.adapter = contactAdapter

        contactItems.clear()
        contactItems.addAll(ContactsManager.contactsList)

        return binding.root
    }

    private fun setLayoutManager() {
        if (isGridMode) {
            binding.RVArea.layoutManager = GridLayoutManager(requireContext(), 3)
        } else {
            binding.RVArea.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
