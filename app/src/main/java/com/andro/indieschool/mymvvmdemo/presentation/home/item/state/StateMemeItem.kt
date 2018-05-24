package com.andro.indieschool.mymvvmdemo.presentation.home.item.state

import com.andro.indieschool.mymvvmdemo.base.ui.item.ItemObserver

interface StateMemeItem: ItemObserver {

    fun showLoading()

    fun showContent()

    fun showError()

}