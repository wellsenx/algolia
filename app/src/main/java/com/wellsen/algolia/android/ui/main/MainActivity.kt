package com.wellsen.algolia.android.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import com.wellsen.algolia.android.R
import com.wellsen.algolia.android.databinding.ActivityMainBinding
import com.wellsen.algolia.android.ui.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = getViewModel()
        vm = binding.vm as MainViewModel
        binding.lifecycleOwner = this


        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                vm.fetchNews(newText)

                return false
            }
        })

    }

}
