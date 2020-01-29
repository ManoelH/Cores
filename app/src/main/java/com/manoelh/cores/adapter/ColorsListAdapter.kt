package com.manoelh.cores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.cores.R
import com.manoelh.cores.viewHolder.ColorsViewHolder

class ColorsListAdapter(private val colorsInHexadecimal: List<String>) : RecyclerView.Adapter<ColorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.list_of_colors , parent, false)
        return ColorsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return colorsInHexadecimal.count()
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.bindColors(colorsInHexadecimal[position])
    }

}