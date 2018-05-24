package com.andro.indieschool.mymvvmdemo.presentation.home.item.state

import android.view.View
import com.andro.indieschool.mymvvmdemo.base.ui.item.BaseItemView
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class StateMemeItemImpl @Inject constructor() : BaseItemView(), StateMemeItem {

    override fun showLoading() {
        updateLoadingVisibility(true)
        updateErrorVisibility(false)
        updateContentVisibility(false)
    }

    override fun showContent() {
        updateLoadingVisibility(false)
        updateErrorVisibility(false)
        updateContentVisibility(true)
    }

    override fun showError() {
        updateLoadingVisibility(false)
        updateErrorVisibility(true)
        updateContentVisibility(false)
    }

    private fun updateLoadingVisibility(isLoading : Boolean) {
        pbMeme.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updateErrorVisibility(isError: Boolean) {
        with(tvError) {
            visibility = if (isError) View.VISIBLE else View.GONE
            text = "ERROR BRUH"
        }
    }

    private fun updateContentVisibility(isLoaded: Boolean) {
        rvMemes.visibility = if (isLoaded) View.VISIBLE else View.GONE
    }
}