package com.example.picturesgrid.ui.img_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.picturesgrid.databinding.FragmentImgBinding
import kotlinx.coroutines.launch

class ImgFragment : Fragment() {

    private lateinit var binding: FragmentImgBinding
    private val args: ImgFragmentArgs by navArgs()
    private val mViewModel = ImageViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImgBinding.inflate(layoutInflater, container, false)
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.setPicture(args.picture)
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            mViewModel.pictureFlow.collect {
                binding.ivPicture.setImageURI(it.uri)
            }
        }
    }
}