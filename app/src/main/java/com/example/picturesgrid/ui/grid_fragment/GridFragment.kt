package com.example.picturesgrid.ui.grid_fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturesgrid.R
import com.example.picturesgrid.data.domain.models.Picture
import com.example.picturesgrid.databinding.FragmentGridBinding
import com.example.picturesgrid.ui.grid_fragment.adapter.PicturesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.io.File

class GridFragment : Fragment(), PicturesAdapter.PictureListener {

    private lateinit var binding: FragmentGridBinding
    private lateinit var mAdapter: PicturesAdapter
    private val mViewModel = GridViewModel()
    private lateinit var imgUri: Uri
    private var idPicture = 0
    private lateinit var mContractCamera: ActivityResultLauncher<Uri>
    private lateinit var mContractGallery: ActivityResultLauncher<String>
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
        setContracts()
        setupAdapter()
        setupViewModel()
        setupToolBar()
    }

    private fun setContracts() {
        mContractCamera =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { userClickSave ->
                if (userClickSave) {
                    mViewModel.addPicture(imgUri)
                }
            }

        mContractGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                mViewModel.addPicture(uri)
            }
        }
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
                        1 -> takePicture()
                    }
                }.show()
            true
        }
    }

    private fun takePicture() {
        imgUri = createImgUri()
        mContractCamera.launch(imgUri)
    }

    private fun showGallery() {
        mContractGallery.launch("image/*")
    }

    override fun onPictureClick(picture: Picture) {
        // TODO: el cick!
    }

    private fun createImgUri(): Uri {
        val img = File(requireContext().filesDir, "$idPicture.png")
        idPicture++
        return FileProvider.getUriForFile(
            requireContext(),
            "com.example.picturesgrid.ui.grid_fragment.FileProvider",
            img
        )
    }
}