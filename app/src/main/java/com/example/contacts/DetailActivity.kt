package com.example.contacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Messenger
import com.bumptech.glide.Glide
import com.example.contacts.Adapter.ContactAdapter
import com.example.contacts.Util.callPhoneNumber
import com.example.contacts.Util.messagePhoneNumber
import com.example.contacts.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

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
            if (detailContact.isNew) {
                Glide.with(binding.root.context)
                    .load(detailContact.profileImageUri)
                    .into(binding.ivUser)
            } else {
                binding.ivUser.setImageResource(detailContact.photo)
            }

            if (detailContact.bookmark) {
                binding.bookmark.setBackgroundResource(R.drawable.clicked_bookmark)
            } else {
                binding.bookmark.setBackgroundResource(R.drawable.unclicked_bookmark)
            }

            binding.bookmark.setOnClickListener {

                detailContact.bookmark = !detailContact.bookmark


                if (detailContact.bookmark) {
                    binding.bookmark.setBackgroundResource(R.drawable.clicked_bookmark)
                    showSnackBarMessage("                                      ⭐즐찾⭐")
                } else {
                    binding.bookmark.setBackgroundResource(R.drawable.unclicked_bookmark)
                    showSnackBarMessage("                                   ⭐즐찾해제⭐")
                }
                val resultIntent = Intent()
                resultIntent.putExtra("BOOKMARK", detailContact.bookmark)
                resultIntent.putExtra("PHONE", detailContact.phoneNumber)

                setResult(RESULT_OK, resultIntent)


            }

            btnCancel.setOnClickListener {
                finish()
            }

            fabCall.setOnClickListener {
                callPhoneNumber(this@DetailActivity, detailContact.phoneNumber)
            }
            fabMessage.setOnClickListener {
                messagePhoneNumber(this@DetailActivity, detailContact.phoneNumber)
            }

        }
    }

    fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.show()

    }

}