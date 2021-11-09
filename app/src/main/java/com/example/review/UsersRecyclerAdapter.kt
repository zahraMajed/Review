package com.example.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.review.RoomDB.UsersEntity
import kotlinx.android.synthetic.main.item_view.view.*

class UsersRecyclerAdapter(private val usersList: List<UsersEntity>):RecyclerView.Adapter<UsersRecyclerAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val userName=usersList[position].userName
        holder.itemView.apply { tvNames.text=userName }
    }

    override fun getItemCount(): Int = usersList.size
}