package com.example.contacts

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(
    dragDirs: Int,
    swipeDirs: Int,
    private val onSwiped: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView

        // 스와이프 색상 지정
        val color = ContextCompat.getColor(itemView.context, R.color.light_main)

        // 스와이프 중에만 아이템의 일부에 배경색을 그려주는 코드
        if (dX > 0) { // 오른쪽으로 스와이프하는 경우
            val paint = Paint()
            paint.color = color

            val background = RectF(
                itemView.left.toFloat(),
                itemView.top.toFloat(),
                itemView.left + dX,
                itemView.bottom.toFloat()
            )

            c.drawRect(background, paint)
        }
    }
}