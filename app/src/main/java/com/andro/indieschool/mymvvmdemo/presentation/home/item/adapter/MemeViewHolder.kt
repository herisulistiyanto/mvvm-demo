package com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.andro.indieschool.mymvvmdemo.base.extension.loadFromUrlWithPlaceholder
import com.andro.indieschool.mymvvmdemo.base.ui.adapter.BaseViewHolder
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto
import com.andro.indieschool.mymvvmdemo.presentation.home.item.MemeItemView
import kotlinx.android.synthetic.main.item_meme.*

class MemeViewHolder(itemView: View, private val actionLiveData: MutableLiveData<MemeItemView.Action>)
    : BaseViewHolder<MemeDto>(itemView) {

    override fun bind(data: MemeDto) {
        setName(data.name)
        setImage(data.imgUrl, data.imgWidth, data.imgHeight)
        setViewClickListener(actionLiveData, data)
    }

    private fun setName(name: String) {
        tvTitle.text = name
    }

    private fun setImage(imgUrl: String, imgWidth: Int, imgHeight: Int) {
        ivMeme.loadFromUrlWithPlaceholder(imgUrl,
                pbThumbnail,
                android.R.drawable.gallery_thumb,
                android.R.drawable.gallery_thumb)
    }

    private fun setViewClickListener(actionLiveData: MutableLiveData<MemeItemView.Action>, data: MemeDto) {
        itemView.setOnClickListener {
            actionLiveData.value = MemeItemView.Action.MemeClicked(data)
        }
    }

}