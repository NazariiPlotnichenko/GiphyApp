package com.example.giphyapp.domain

import com.example.giphyapp.api.MainApi
import com.example.giphyapp.dataClasses.Gifs

class GifRepository(val mainApi: MainApi) {
    private val apiKey = "YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL"
    private val limit = 50
    private val offset = 0
    private val rating = "g"
    private val lang = "en"
    suspend fun getTrendingGifs(): Gifs {
        return mainApi.getTrendingGifs(apiKey, limit, offset, rating)
    }

    suspend fun getSearchGifs(query: String): Gifs {
        return mainApi.getSearchGifs(apiKey, query, limit, offset, rating, lang)
    }
}