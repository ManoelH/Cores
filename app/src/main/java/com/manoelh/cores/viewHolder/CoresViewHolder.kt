package com.manoelh.cores.viewHolder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.cores.R

class CoresViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){

    private val retangulo = itemView.findViewById<ImageView>(R.id.imageViewCor)

    fun vincularCores(corDaLista: String){
        retangulo.setBackgroundColor(Color.parseColor(corDaLista))
    }
}