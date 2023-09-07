package com.example.giphyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.giphyapp.databinding.FullscreengifBinding

class FullScreenGifFragment : Fragment(R.layout.fullscreengif) {
    private lateinit var binding: FullscreengifBinding

    companion object {
        private const val URL_EXTRA = "gif_url"
        fun newInstance(url: String): FullScreenGifFragment {
            val fragment = FullScreenGifFragment()
            val args = Bundle()
            args.putString(URL_EXTRA, url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullscreengifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(URL_EXTRA)
        Glide.with(binding.root).load(url).into(binding.fullGif)
    }
}