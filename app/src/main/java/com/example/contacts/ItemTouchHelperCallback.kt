package com.example.contacts

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(
    dragDirs: Int,
    swipeDirs: Int,
    private val onSwiped: (Int) -> Unit,
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    var onSwipeListener: ((Float, RecyclerView.ViewHolder) -> Unit)? = null
    var onSwipe: ((Float, Int) -> Unit)? = null

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwiped(viewHolder.adapterPosition)
    }

    // 스와이프 디자인
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val itemView = viewHolder.itemView
        val background = itemView.findViewById<View>(R.id.swipeBackground)
        val layoutParams = background.layoutParams

        // 스와이프 네모 뷰 그림
        layoutParams?.apply {
            width = if (dX > 0) {
                dX.toInt()
            } else {
                0
            }
            background.layoutParams = this
        }
    }
}