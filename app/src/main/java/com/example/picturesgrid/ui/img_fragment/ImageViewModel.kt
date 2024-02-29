package com.example.picturesgrid.ui.img_fragment

import androidx.lifecycle.ViewModel
import com.example.picturesgrid.data.domain.models.Picture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ImageViewModel : ViewModel() {

    private val _pictureFlow = MutableStateFlow(Picture())
    val pictureFlow: StateFlow<Picture> = _pictureFlow

    private val picture = Picture()

    fun setPicture(picture: Picture) {
        _pictureFlow.value = picture
    }
}