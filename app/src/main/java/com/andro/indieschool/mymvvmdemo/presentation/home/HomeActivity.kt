package com.andro.indieschool.mymvvmdemo.presentation.home

import android.os.Bundle
import androidx.core.widget.toast
import com.andro.indieschool.mymvvmdemo.R
import com.andro.indieschool.mymvvmdemo.base.extension.getContentView
import com.andro.indieschool.mymvvmdemo.base.extension.observe
import com.andro.indieschool.mymvvmdemo.base.ui.BaseActivity
import com.andro.indieschool.mymvvmdemo.presentation.home.item.MemeItemView
import com.andro.indieschool.mymvvmdemo.presentation.home.item.state.StateMemeItem
import com.andro.indieschool.mymvvmdemo.presentation.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity(override val layoutResourceId: Int = R.layout.activity_home) : BaseActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var memeItemView: MemeItemView

    @Inject
    lateinit var stateMemeItem: StateMemeItem

    override fun onViewReady(savedInstanceState: Bundle?) {
        setupMemeItemCycleObserver()
        setupMemeItemStateCycle()
        setupViewModelStateObserver()
        viewModel.fetchMemes()
    }

    private fun setupMemeItemCycleObserver() {
        memeItemView.initItemLifeCycle(lifecycle, getContentView())
        observe(memeItemView.getAction()) {
            when (it) {
                is MemeItemView.Action.MemeClicked -> toast(it.meme.name)
            }
        }
    }

    private fun setupMemeItemStateCycle() {
        stateMemeItem.initItemLifeCycle(lifecycle, getContentView())
    }

    private fun setupViewModelStateObserver() {
        observe(viewModel.getState()) {
            when (it) {
                is HomeViewModel.State.MemeLoaded -> memeItemView.showMemes(it.memes)
                is HomeViewModel.State.ShowLoading -> stateMemeItem.showLoading()
                is HomeViewModel.State.ShowContent -> stateMemeItem.showContent()
                is HomeViewModel.State.ShowError -> stateMemeItem.showError()
            }
        }
    }
}
