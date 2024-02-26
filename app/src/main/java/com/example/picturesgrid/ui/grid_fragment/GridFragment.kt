package com.example.picturesgrid.ui.grid_fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturesgrid.R
import com.example.picturesgrid.data.constants.Constants.REQUEST_GALLERY
import com.example.picturesgrid.data.domain.models.Picture
import com.example.picturesgrid.databinding.FragmentGridBinding
import com.example.picturesgrid.ui.grid_fragment.adapter.PicturesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class GridFragment : Fragment(), PicturesAdapter.PictureListener {

    private lateinit var binding: FragmentGridBinding
    private lateinit var mAdapter: PicturesAdapter
    private val mViewModel = GridViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGridBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupViewModel()
        setupToolBar()

    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            mViewModel.picturesFlow.collect {
                mAdapter.refreshData(it)
            }
        }
    }

    private fun setupAdapter() {
        mAdapter = PicturesAdapter(mutableListOf(), this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = mAdapter
    }

    private fun setupToolBar() {
        binding.toolBar.inflateMenu(R.menu.menu_add)
        binding.toolBar.menu.getItem(0).setOnMenuItemClickListener {

            val items = resources.getStringArray(R.array.array_options_item)

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialog_options_title)
                .setItems(items) { _, which ->
                    when (which) {
                        0 -> showGallery()
                        1 -> {} // TODO: decírselo al viewModel
                    }
                }.show()
            true
        }
    }

    private fun showGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            val uri = data.data
            mViewModel.addPicture(uri)
        }
    }

    override fun onPictureClick(picture: Picture) {
        // TODO: el cick!
    }
}