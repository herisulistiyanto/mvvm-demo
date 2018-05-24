package com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.andro.indieschool.mymvvmdemo.R
import com.andro.indieschool.mymvvmdemo.base.ui.adapter.DiffCallback
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto
import com.andro.indieschool.mymvvmdemo.presentation.home.item.MemeItemView
import javax.inject.Inject

class MemesAdapterImpl @Inject constructor(private val diffCallback: DiffCallback,
                                           private val actionLiveData: MutableLiveData<MemeItemView.Action>)
    : MemesAdapter() {

    private var memes = mutableListOf<MemeDto>()

    override fun setMemes(memes: List<MemeDto>) {
        calculateDiff(ArrayList(memes))
    }

    override fun addMemes(memes: List<MemeDto>) {
        val list = ArrayList(this.memes)
        list.addAll(memes)
        calculateDiff(list)
    }

    override fun clearMemes() {
        calculateDiff(emptyList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_meme, parent, false)
        return MemeViewHolder(itemView, actionLiveData)
    }

    override fun getItemCount(): Int = memes.size

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.bind(memes[holder.adapterPosition])
    }

    private fun calculateDiff(memes: List<MemeDto>) {
        diffCallback.setList(this.memes, memes)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(this.memes) {
            clear()
            addAll(memes)
        }
        result.dispatchUpdatesTo(this)
    }
}