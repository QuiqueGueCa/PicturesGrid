package com.example.picturesgrid.ui.grid_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picturesgrid.R
import com.example.picturesgrid.data.domain.models.Picture
import com.example.picturesgrid.databinding.ItemRecyclerviewImgBinding

class PicturesAdapter(
    private val pictures: MutableList<Picture>,
    private val listener: PictureListener
) : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    interface PictureListener {
        fun onPictureClick(picture: Picture)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRecyclerviewImgBinding.bind(view)
        fun setListener(position: Int) {
            binding.root.setOnClickListener {
                listener.onPictureClick(pictures[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recyclerview_img, parent, false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgPicture.setImageURI(pictures[position].uri)
        holder.setListener(position)
    }

    override fun getItemCount(): Int = pictures.count()

    fun refreshData(newList: MutableList<Picture>) {
        pictures.clear()
        pictures.addAll(newList)
        notifyDataSetChanged()
    }
}