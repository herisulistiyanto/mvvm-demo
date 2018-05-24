package com.andro.indieschool.mymvvmdemo.base.ui.adapter

import android.support.v7.util.DiffUtil
import javax.inject.Inject

open class DiffCallback @Inject constructor(): DiffUtil.Callback() {

    private var oldList: List<Any> = emptyList()
    private var newList: List<Any> = emptyList()

    fun setList(oldList: List<Any>, newList: List<Any>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            (!(oldItemPosition >= oldList.size || newItemPosition >= newList.size)
                    && oldList[oldItemPosition] == newList[newItemPosition])

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            (!(oldItemPosition >= oldList.size || newItemPosition >= newList.size)
                    && oldList[oldItemPosition] == newList[newItemPosition])
}