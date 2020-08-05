package com.wayne.cmoneytask.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wayne.cmoneytask.R
import com.wayne.cmoneytask.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoRequest.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPhotosFragment())
        }
    }
}