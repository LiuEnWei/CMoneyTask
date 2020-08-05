package com.wayne.cmoneytask.ui.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wayne.cmoneytask.R
import com.wayne.cmoneytask.model.api.vo.Photo
import com.wayne.cmoneytask.ui.adapter.OnPhotoClickListener
import com.wayne.cmoneytask.ui.adapter.PhotosAdapter
import com.wayne.cmoneytask.ui.base.BaseFragment
import com.wayne.cmoneytask.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment: BaseFragment(R.layout.fragment_photos), OnPhotoClickListener {
    private val viewModel by viewModels<PhotosViewModel>()
    private var adapter: PhotosAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerPhotos.layoutManager = GridLayoutManager(requireContext(), 4)
        adapter = PhotosAdapter(lifecycleScope = lifecycleScope, onPhotoClickListener = this)
        recyclerPhotos.adapter = adapter

        viewModel.getPhotos()
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            adapter?.photos = it
            adapter?.notifyDataSetChanged()
        })
    }

    override fun onClick(photo: Photo) {
        findNavController().navigate(PhotosFragmentDirections.actionPhotosFragmentToDetailFragment(photo))
    }
}