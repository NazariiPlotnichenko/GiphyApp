package com.example.giphyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyapp.adapter.GifAdapter
import com.example.giphyapp.dataClasses.Gif
import com.example.giphyapp.databinding.GiflistfragmentBinding
import com.example.giphyapp.domain.GifRepository
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GifListFragment : Fragment(R.layout.giflistfragment), KoinComponent {
    private lateinit var binding: GiflistfragmentBinding
    private lateinit var adapter: GifAdapter
    private var jobSearch: Job? = null
    private var jobTrending: Job? = null
    private val gifRepository: GifRepository by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GiflistfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GifAdapter { gif: Gif ->
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, FullScreenGifFragment.newInstance(gif.images.original.url))
                .addToBackStack(null)
                .commit()
        }

        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcView.adapter = adapter

        jobTrending = CoroutineScope(Dispatchers.IO).launch {
            val gifsObjectList = gifRepository.getTrendingGifs()
            requireActivity().runOnUiThread {
                binding.apply {
                    adapter.submitList(gifsObjectList.data)
                }
            }
        }

        binding.SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                jobSearch?.cancel()
                jobSearch = CoroutineScope(Dispatchers.IO).launch {
                    val gifsObjectList = text?.let {
                        gifRepository.getSearchGifs(it)
                    }
                    requireActivity().runOnUiThread {
                        binding.apply {
                            adapter.submitList(gifsObjectList?.data)
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    override fun onDestroyView() {
        jobSearch?.cancel()
        jobTrending?.cancel()
        super.onDestroyView()
    }
}