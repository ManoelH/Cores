package com.manoelh.cores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.cores.R
import com.manoelh.cores.viewHolder.CoresViewHolder

class ListaDeCoresAdapter(private val coresEmHexadecimal: List<String>) : RecyclerView.Adapter<CoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoresViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.lista_de_cores , parent, false)
        return CoresViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return coresEmHexadecimal.count()
    }

    override fun onBindViewHolder(holder: CoresViewHolder, position: Int) {
        holder.vincularCores(coresEmHexadecimal[position])
    }

}