package com.example.contacts

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.contacts.databinding.FragmentMyPageBinding
import de.hdodenhof.circleimageview.CircleImageView

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding


    private  val EDIT_IMAGE_REQUEST_CODE_MY = 5
    private var selectedImageUri: Uri? = null
    private lateinit var editImage: ImageView
    //kotlin lateinit check

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        binding.editBtn.setOnClickListener {

            val builder = AlertDialog.Builder((activity as MainActivity))
            builder.setTitle("EDIT")

            val v1 = layoutInflater.inflate(R.layout.edit_dialog, null)
            builder.setView(v1)

            val editName: EditText = v1.findViewById(R.id.et_edit_name)
            val editPhoneNumber: EditText = v1.findViewById(R.id.et_edit_phone_number)
            val editEmail: EditText = v1.findViewById(R.id.et_edit_email)
            val ibEditImg: ImageButton = v1.findViewById(R.id.ib_edit_img)
            editImage = v1.findViewById(R.id.iv_edit_img)
            editImage.setImageDrawable(binding.ivUser.drawable)

            // 갤러리에서 이미지 선택을 위한 코드
            ibEditImg.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, EDIT_IMAGE_REQUEST_CODE_MY)
            }

            // p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
            val listener = DialogInterface.OnClickListener { p0, p1 ->
                val alert = p0 as AlertDialog

                // 값 변경
                binding.tvName.text = editName.text.toString()
                binding.tvMobile.text = editPhoneNumber.text.toString()
                binding.tvEmail.text = editEmail.text.toString()

                // 이미지 설정
                selectedImageUri?.let { uri ->
                    editImage.setImageURI(uri)
                    binding.ivUser.setImageURI(uri) // ivUser에도 이미지 설정
                }

                // 다이얼로그 닫기
                alert.dismiss()
            }

            // 확인 버튼을 다이얼로그에 대한 클릭 리스너로 설정
            builder.setPositiveButton("확인", listener)

            val alertDialog = builder.create() // AlertDialog를 생성합니다.

            alertDialog.show()
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_IMAGE_REQUEST_CODE_MY && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            try {
                editImage.setImageURI(selectedImageUri)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "이미지 설정에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

