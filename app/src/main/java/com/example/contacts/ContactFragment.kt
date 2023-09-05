// ContactFragment.kt
package com.example.contacts


import android.app.AlertDialog
import android.os.Bundle

import ContactAdapter

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private var isGridMode = false
    private val contactItems: MutableList<Contact> = mutableListOf()

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


        binding.RVArea.adapter = contactAdapter

        // [3]에서 floatingBtn을 클릭할 때 다이얼로그를 표시
        binding.floatingBtn.setOnClickListener {
            showAddContactDialog()
        }

        contactItems.clear()
        contactItems.addAll(ContactsManager.contactsList)


        return binding.root
    }

    // 다이얼로그를 표시하는 함수
    private fun showAddContactDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_contact_dialog, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val nameEdit = dialogView.findViewById<EditText>(R.id.nameEdit)
        val numberEdit = dialogView.findViewById<EditText>(R.id.numberEdit)
        val emailEdit = dialogView.findViewById<EditText>(R.id.eMailEdit)
        val eventEdit = dialogView.findViewById<EditText>(R.id.eventEdit)

        dialogView.findViewById<Button>(R.id.saveBt)?.setOnClickListener {
            val name = nameEdit.text.toString()
            val phoneNumber = numberEdit.text.toString()
            val email = emailEdit.text.toString()
            val event = eventEdit.text.toString()

            // 필수 정보가 입력되었는지 확인
            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty()&& event.isNotEmpty()) {
                // Contact로 사용자 입력 정보 전달
                ContactsManager.addContact(name, phoneNumber, email, event)

                // 다이얼로그 닫기
                dialog.dismiss()

                // RecyclerView 업데이트
                contactAdapter.notifyDataSetChanged()
            } else {
                // 필수 정보가 입력되지 않은 경우 토스트 메시지 표시
                Toast.makeText(requireContext(), "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<Button>(R.id.cancelBt)?.setOnClickListener {
            // 다이얼로그 닫기
            dialog.dismiss()
        }

        dialog.show()
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
