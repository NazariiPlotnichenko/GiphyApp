package com.example.giphyapp.api

import com.example.giphyapp.dataClasses.Gifs
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
    ): Gifs

    @GET("gifs/search")
    suspend fun getSearchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): Gifs
}