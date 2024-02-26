package com.example.picturesgrid.ui.grid_fragment

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.picturesgrid.data.domain.models.Picture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GridViewModel : ViewModel() {

    private val _picturesFlow = MutableStateFlow<MutableList<Picture>>(mutableListOf())
    val picturesFlow: StateFlow<MutableList<Picture>> = _picturesFlow

    private val pictures = mutableListOf<Picture>()
    fun addPicture(uri: Uri?) {
        if (uri != null) {
            val picture = Picture(uri)
            pictures.add(picture)
            _picturesFlow.value = mutableListOf()
            _picturesFlow.value = pictures
        }
    }
}