package com.example.draganddrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class ItemMoveCallbackListener(val adapter: DragDropRecyclerAdapter) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.START
        return makeMovementFlags(dragFlags, swipeFlag)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is DragDropRecyclerAdapter.ItemViewHolder) {
                adapter.onRowSelected(viewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is DragDropRecyclerAdapter.ItemViewHolder) {
            adapter.onRowClear(viewHolder)
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (viewHolder is DragDropRecyclerAdapter.ItemViewHolder) {
            adapter.onItemDismissed(viewHolder.adapterPosition)
        }
    }

    interface Listener {
        fun onRowMoved(fromPosition: Int, toPosition: Int)
        fun onRowSelected(itemViewHolder: DragDropRecyclerAdapter.ItemViewHolder)
        fun onRowClear(itemViewHolder: DragDropRecyclerAdapter.ItemViewHolder)
        fun onItemDismissed(position: Int)
    }
}