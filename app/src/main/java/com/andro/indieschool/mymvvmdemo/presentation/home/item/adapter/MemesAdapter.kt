package com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter

import android.support.v7.widget.RecyclerView
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto

abstract class MemesAdapter : RecyclerView.Adapter<MemeViewHolder>() {

    abstract fun setMemes(memes: List<MemeDto>)

    abstract fun addMemes(memes: List<MemeDto>)

    abstract fun clearMemes()

}