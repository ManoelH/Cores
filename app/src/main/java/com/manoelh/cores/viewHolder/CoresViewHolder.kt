package com.manoelh.cores.viewHolder

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.cores.R


class CoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val retangulo = itemView.findViewById<ImageView>(R.id.imageViewCor)

    fun vincularCores(corDaLista: String){
        val shape: GradientDrawable =  retangulo.drawable as GradientDrawable
        shape.setColor(Color.parseColor(corDaLista))
    }
}