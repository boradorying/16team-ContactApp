package com.example.contacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contacts.Util.callPhoneNumber

import com.example.contacts.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        private lateinit var detailContact: Contact
        fun newIntentForDetail(context: Context?, contact: Contact) =
            Intent(context, DetailActivity::class.java).apply {
                detailContact = contact
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvMobile.text = detailContact.phoneNumber
            tvEmail.text = detailContact.email
            tvName.text = detailContact.name
            ivUser.setImageResource(detailContact.photo)

            btnCancel.setOnClickListener {
                finish()
            }

            fabCall.setOnClickListener {
                callPhoneNumber(this@DetailActivity, detailContact.phoneNumber)
            }
        }
    }
}