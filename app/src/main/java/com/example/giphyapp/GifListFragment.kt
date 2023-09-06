package com.example.giphyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyapp.adapter.GifAdapter
import com.example.giphyapp.api.MainApi
import com.example.giphyapp.databinding.GiflistfragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GifListFragment : Fragment(R.layout.giflistfragment) {
    private lateinit var binding: GiflistfragmentBinding
    private lateinit var adapter: GifAdapter
    private val apiKey = "YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL"
    private val limit = 25
    private val offset = 0
    private val rating = "g"
    private val lang = "en"

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: GiflistfragmentBinding = GiflistfragmentBinding.inflate(inflater, container, false)
//        return binding.getRoot()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GiflistfragmentBinding.inflate(layoutInflater)

        adapter = GifAdapter()
        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcView.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gifApi = retrofit.create(MainApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val gifsObjectList = gifApi.getTrendingGifs(apiKey, limit, offset, rating)
            requireActivity().runOnUiThread {
                binding.apply {
                    adapter.submitList(gifsObjectList.data)
                }
            }
        }

        binding.SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val gifsObjectList = text?.let {
                        gifApi.getSearchGifs(apiKey, it, limit, offset, rating, lang)
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
}