package com.andro.indieschool.mymvvmdemo.presentation.home.item

import android.arch.lifecycle.LiveData
import com.andro.indieschool.mymvvmdemo.base.ui.item.ItemObserver
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto

interface MemeItemView: ItemObserver {

    sealed class Action {
        data class MemeClicked(val meme: MemeDto) : Action()
    }

    fun getAction(): LiveData<Action>

    fun showMemes(memes: List<MemeDto>)

}