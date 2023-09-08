package com.example.contacts.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.contacts.Contact
import com.example.contacts.R
import com.example.contacts.databinding.RvGridItemBinding
import com.example.contacts.databinding.RvItemBinding

class ContactAdapter(
    private var contact: MutableList<Contact>,
    private val isGridMode: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_LIST = 1
    private val VIEW_TYPE_GRID = 2

    // itemClick(ms)
    var productClick: ProductClick? = null
    interface ProductClick {
        fun onClick(view: View, position: Int)
    }
    // itemClick(ms)
    fun updateContactList(newList: List<Contact>) {
        contact.clear()
        contact.addAll(newList)
        notifyDataSetChanged()
    }//프래그먼트에서  함수호출하는거임

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LIST -> {
                val binding = RvItemBinding.inflate(inflater, parent, false)
                ListViewHolder(binding)
            }
            VIEW_TYPE_GRID -> {
                val binding = RvGridItemBinding.inflate(inflater, parent, false)
                GridViewHolder(binding)
            }
            else -> throw IllegalArgumentException("")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun getItemCount(): Int {
        return contact.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        initItemViewHolder(holder)
        when (holder.itemViewType) {
            VIEW_TYPE_LIST -> {
                val normalViewHolder = holder as ListViewHolder
                normalViewHolder.bindItems(contact[position])
            }
            VIEW_TYPE_GRID -> {
                val gridViewHolder = holder as GridViewHolder
                gridViewHolder.bindItems(contact[position])
            }
        }

        // itemClick(ms)
        holder.itemView.setOnClickListener {
            productClick?.onClick(it, position)
        }
        // itemClick(ms)
    }

    private fun initItemViewHolder(holder: ViewHolder) {
       val background = holder.itemView.findViewById<View>(R.id.swipeBackground)
        val layoutParams = background?.layoutParams

        // 스와이프 네모 뷰 그림 초기화
        layoutParams?.apply {
            width = 0
            background.layoutParams = this
        }
    }

    inner class ListViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.bookmark.setOnClickListener {
                val item = contact[adapterPosition]
                item.bookmark = !item.bookmark

                notifyItemChanged(adapterPosition)
            }
        }

        fun bindItems(item: Contact) {

            if (item.isNew){
                Glide.with(binding.root.context)
                    .load(item.profileImageUri)
                    .into(binding.imageArea)
            }
            else{
                binding.imageArea.setImageResource(item.photo)
            }
            binding.nameArea.text = item.name

            if (item.bookmark) {
                binding.bookmark.setBackgroundResource(R.drawable.clicked_bookmark)

            } else {
                binding.bookmark.setBackgroundResource(R.drawable.unclicked_bookmark)

            }
        }
    }

    inner class GridViewHolder(private val binding: RvGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Contact) {
            if (item.isNew){
                Glide.with(binding.root.context)
                    .load(item.profileImageUri)
                    .into(binding.imageArea)
            }
            else{
                binding.imageArea.setImageResource(item.photo)
            }
            binding.nameArea.text = item.name
        }
    }
}
