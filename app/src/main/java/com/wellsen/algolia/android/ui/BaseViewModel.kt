package com.wellsen.algolia.android.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}
