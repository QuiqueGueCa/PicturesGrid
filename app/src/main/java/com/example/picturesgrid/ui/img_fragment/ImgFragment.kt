package com.example.picturesgrid.ui.img_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.picturesgrid.databinding.FragmentImgBinding

class ImgFragment : Fragment() {

    private lateinit var binding: FragmentImgBinding
    private val args: ImgFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImgBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picture = args.picture
        binding.ivPicture.setImageURI(picture.uri)
    }
}