package com.wellsen.algolia.android.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
    }

}
