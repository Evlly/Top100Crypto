package com.example.top100crypto.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH: BaseAdapter.BaseViewHolder>: RecyclerView.Adapter<VH>() {

    var items = ArrayList<Any>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int): Any{
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(newItem: Any){
        items.add(newItem)
    }

    fun add(newItems: List<Any>){
        items.addAll(newItems)
    }

    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(item: Any)
    }
}