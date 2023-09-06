package com.example.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.databinding.FragmentEditMyPageBinding
import com.example.contacts.databinding.FragmentMyPageBinding


class EditMyPageFragment : Fragment() {
    private lateinit var binding: FragmentEditMyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMyPageBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", binding.tv3.text.toString())
            bundle.putString("phone", binding.tv1.text.toString())
            bundle.putString("email", binding.tv2.text.toString())

            val myPageFragment = MyPageFragment()
            myPageFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.myPageContainer, myPageFragment)
                .commit()
        }







        return binding.root
    }


}