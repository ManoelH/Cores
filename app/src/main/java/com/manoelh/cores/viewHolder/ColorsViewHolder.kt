package com.manoelh.cores.viewHolder

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.cores.R


class ColorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val rectangle = itemView.findViewById<ImageView>(R.id.imageViewCor)

    fun bindColors(colorsFromList: String){
        val shape: GradientDrawable =  rectangle.drawable as GradientDrawable
        shape.setColor(Color.parseColor(colorsFromList))
    }
}