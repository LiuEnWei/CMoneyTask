package com.wayne.cmoneytask.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wayne.cmoneytask.R
import com.wayne.cmoneytask.model.imageloader.ImageLoader
import com.wayne.cmoneytask.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: BaseFragment(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val photo = DetailFragmentArgs.fromBundle(it).photo
            textId.text = getString(R.string.text_id, photo.id.toString())
            textTitle.text = getString(R.string.text_title, photo.title)
            ImageLoader.with(requireContext()).displayImage(photo.url, lifecycleScope, imgUrl)
        }

    }
}