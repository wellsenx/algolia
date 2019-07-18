package com.wellsen.algolia.android.module

import com.wellsen.algolia.android.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
