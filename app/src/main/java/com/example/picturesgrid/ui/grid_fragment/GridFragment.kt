package com.example.picturesgrid.ui.grid_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturesgrid.R
import com.example.picturesgrid.data.Picture
import com.example.picturesgrid.databinding.FragmentGridBinding
import com.example.picturesgrid.ui.grid_fragment.adapter.PicturesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GridFragment : Fragment(), PicturesAdapter.PictureListener {

    private lateinit var binding: FragmentGridBinding
    private lateinit var mAdapter: PicturesAdapter
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

        setupToolBar()
        setupAdapter()
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
                        0 -> {} // TODO: decírselo al viewModel
                        1 -> {} // TODO: decírselo al viewModel
                    }
                }.show()
            true
        }
    }

    override fun onPictureClick(picture: Picture) {
        // TODO: el cick!
    }
}