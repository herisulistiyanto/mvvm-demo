package com.andro.indieschool.mymvvmdemo.presentation.home.item

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.LinearLayoutManager
import com.andro.indieschool.mymvvmdemo.base.ui.item.BaseItemView
import com.andro.indieschool.mymvvmdemo.data.mapper.MemeDto
import com.andro.indieschool.mymvvmdemo.presentation.home.item.adapter.MemesAdapter
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class MemeItemViewImpl @Inject constructor(private val actionLiveData: MutableLiveData<MemeItemView.Action>,
                                           private val layoutManager: LinearLayoutManager,
                                           private val memesAdapter: MemesAdapter)
    : BaseItemView(), MemeItemView {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(rvMemes) {
            layoutManager = this@MemeItemViewImpl.layoutManager
            adapter = memesAdapter
        }
    }

    override fun getAction(): LiveData<MemeItemView.Action> = actionLiveData

    override fun showMemes(memes: List<MemeDto>) {
        memesAdapter.setMemes(memes)
    }
}