package com.wellsen.algolia.android.util

import androidx.lifecycle.MutableLiveData

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
class NonNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue() = super.getValue()!!

}
