package com.example.draganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.DragStartHelper
import androidx.core.view.DragStartHelper.OnDragStartListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DragDropRecyclerAdapter.OnStartDragListener {

    private lateinit var adapter: DragDropRecyclerAdapter
    private lateinit var adapter2: DragDropRecyclerAdapter
    private lateinit var touchHelper: ItemTouchHelper
    private lateinit var touchHelper2:ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DragDropRecyclerAdapter(this)
        adapter2 = DragDropRecyclerAdapter(this)
        populateListItem()
        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val callback2: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter2)
        touchHelper2 = ItemTouchHelper(callback2)
        touchHelper2.attachToRecyclerView(recyclerView2)
        recyclerView2.adapter = adapter2

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
        touchHelper2.startDrag(viewHolder)
    }

    private fun populateListItem() {
        val users = listOf(
            "Anuj",
            "Bhanu",
            "Chetan",
            "Devendra",
            "Esha",
            "Farmod",
            "Ganesh",
            "Hemant",
            "Ishaan",
            "Jack",
            "Kamal",
            "Lalit",
            "Mona"
        )
        adapter.setUsers(users)


        val users2 = listOf(
            "Photo",
            "Video",
            "Pro",
            "Panorama",
            "Slow Mothion",
            "More"
        )
        adapter2.setUsers(users2)

    }
}
