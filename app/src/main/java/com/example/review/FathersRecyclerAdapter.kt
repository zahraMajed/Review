package com.example.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.review.Retrofit.FathersAPIModel
import kotlinx.android.synthetic.main.item_view.view.*

class FathersRecyclerAdapter (private val fathersList: List<FathersAPIModel.FathersAPIModelItem>): RecyclerView.Adapter<FathersRecyclerAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val fatherName= fathersList[position].name
        holder.itemView.apply { tvNames.text=fatherName }

    }

    override fun getItemCount(): Int=fathersList.size

}