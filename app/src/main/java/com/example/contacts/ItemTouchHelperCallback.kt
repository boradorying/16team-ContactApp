package com.example.contacts

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(
    dragDirs: Int,
    swipeDirs: Int,
    private val onSwiped: (Int) -> Unit,
    private val isGridMode: Boolean
) : ItemTouchHelper.SimpleCallback(if (isGridMode) 0 else dragDirs, if (isGridMode) 0 else swipeDirs) {

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
        val background = itemView.findViewById<View>(R.id.swipeBackground) ?: return

        // 그리드뷰 에선 스와이프 디자인을 그리지 않음

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