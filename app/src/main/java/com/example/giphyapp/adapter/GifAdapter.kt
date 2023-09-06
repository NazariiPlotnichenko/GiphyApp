package com.example.giphyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.R
import com.example.giphyapp.dataClasses.Gif
import com.example.giphyapp.databinding.GifItemBinding



class GifAdapter : ListAdapter<Gif, GifAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = GifItemBinding.bind(view)
        fun bind(gif: Gif) = with(binding) {
            //Glide.with(root).load(gif.images.original.url).into(gifImageView)
            binding.textView.text = gif.images.original.url
        }
    }

    class Comparator : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.images.original.url == newItem.images.original.url
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gif_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}