package com.andro.indieschool.mymvvmdemo.base.ui.item

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.view.View

interface ItemObserver: LifecycleObserver {

    fun initItemLifeCycle(lifecycle: Lifecycle, view: View)

}