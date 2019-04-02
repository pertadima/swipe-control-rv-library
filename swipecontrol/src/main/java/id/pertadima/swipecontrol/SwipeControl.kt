package id.pertadima.swipecontrol

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pertadima on 02,April,2019
 */

abstract class SwipeControl(
    private val context: Context,
    private val leftIcon: Int,
    private val rightIcon: Int,
    private val leftBackgroundColor: String = "#019D3C",
    private val rightBackgroundColor: String = "#EE2B19"
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    companion object {
        const val SWIPE_RIGHT = 8
        const val SWIPE_LEFT = 4
        const val DEFAULT_NULL_VALUE = 0
        const val DIVIDE_BY_TWO = 2
    }

    private var icon = ContextCompat.getDrawable(context, leftIcon)
    private val intrinsicWidth = icon?.intrinsicWidth ?: DEFAULT_NULL_VALUE
    private val intrinsicHeight = icon?.intrinsicHeight ?: DEFAULT_NULL_VALUE
    private val background = ColorDrawable()
    private var backgroundColor = Color.parseColor(rightBackgroundColor)
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }


    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (dX > 0) {
            icon = ContextCompat.getDrawable(context, leftIcon)
            backgroundColor = Color.parseColor(leftBackgroundColor)
            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top
            val isCanceled = dX == 0f && !isCurrentlyActive

            if (isCanceled) {
                clearCanvas(
                    c,
                    itemView.left + dX,
                    itemView.top.toFloat(),
                    itemView.left.toFloat(),
                    itemView.bottom.toFloat()
                )
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                return
            }

            // Draw the green check background
            background.color = backgroundColor
            background.setBounds(itemView.left + dX.toInt(), itemView.top, itemView.left, itemView.bottom)
            background.draw(c)

            // Calculate position of delete icon
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / DIVIDE_BY_TWO
            val deleteIconLeft = itemView.left + intrinsicWidth
            val deleteIconRight = itemView.left + DIVIDE_BY_TWO * intrinsicWidth
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            // Draw the delete icon
            icon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            icon?.draw(c)
        } else {
            icon = ContextCompat.getDrawable(context, rightIcon)
            backgroundColor = Color.parseColor(rightBackgroundColor)
            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top
            val isCanceled = dX == 0f && !isCurrentlyActive

            if (isCanceled) {
                clearCanvas(
                    c,
                    itemView.right + dX,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                return
            }

            // Draw the red delete background
            background.color = backgroundColor
            background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            background.draw(c)

            // Calculate position of delete icon
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / DIVIDE_BY_TWO
            val deleteIconMargin = (itemHeight - intrinsicHeight) / DIVIDE_BY_TWO
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            // Draw the delete icon
            icon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            icon?.draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }
}