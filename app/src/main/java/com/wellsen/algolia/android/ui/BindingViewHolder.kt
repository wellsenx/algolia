package com.wellsen.algolia.android.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
abstract class BindingViewHolder<out T : ViewDataBinding>(view: View) :
    RecyclerView.ViewHolder(view) {

    val binding: T = DataBindingUtil.bind(view)!!

}
