package com.example.giphyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapp.R
import com.example.giphyapp.dataClasses.Gif
import com.example.giphyapp.databinding.ListGifBinding

class GifAdapter : ListAdapter<Gif, GifAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ListGifBinding.bind(view)

        fun bind(gif: Gif) = with(binding){
            textView.text = gif.embed_url
        }
    }
    class Comparator: DiffUtil.ItemCallback<Gif>(){
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_gif, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}