package com.example.picturesgrid.ui.grid_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.picturesgrid.R
import com.example.picturesgrid.databinding.FragmentGridBinding

class GridFragment : Fragment() {

    private lateinit var binding: FragmentGridBinding
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
        
    }

    private fun setupToolBar() {
        binding.toolBar.inflateMenu(R.menu.menu_add)
        binding.toolBar.menu.getItem(0).setOnMenuItemClickListener {
            Toast.makeText(requireContext(), "asdadsasds", Toast.LENGTH_SHORT).show()
            true
        }
    }
}