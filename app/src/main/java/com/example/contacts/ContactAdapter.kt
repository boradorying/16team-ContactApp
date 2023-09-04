package com.example.contacts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.contacts.databinding.RvItemBinding

class ContactAdapter(
    private var contact: MutableList<Contact>,
    private val isGridMode: Boolean
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private val VIEW_TYPE_LIST = 1
    private val VIEW_TYPE_GRID = 2

    //itemClick(ms)
    var productClick: ProductClick? = null
    interface ProductClick {
        fun onClick(view: View, position: Int)
    }
    //itemClick(ms)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = if (viewType == VIEW_TYPE_GRID) {
            R.layout.rv_grid_item
        } else {
            R.layout.rv_item
        }

        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val binding = RvItemBinding.bind(itemView)

        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun getItemCount(): Int {
        return contact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(contact[position])

        //itemClick(ms)
        holder.itemView.setOnClickListener {
            productClick?.onClick(it, position)
        }
        //itemClick(ms)

    }

    inner class ViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.bookmark.setOnClickListener {
                val item = contact[adapterPosition]
                item.bookmark = !item.bookmark

                notifyItemChanged(adapterPosition)
            }
        }

        fun bindItems(item: Contact) {
            binding.imageArea.setImageResource(item.photo)
            binding.nameArea.text = item.name
            if (item.bookmark) {
                binding.bookmark.setBackgroundResource(R.drawable.clicked_bookmark)
            } else {
                binding.bookmark.setBackgroundResource(R.drawable.unclicked_bookmark)
            }
        }
    }
}
