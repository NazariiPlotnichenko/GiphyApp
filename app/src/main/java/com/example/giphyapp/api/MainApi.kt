package com.example.giphyapp.api

import com.example.giphyapp.dataClasses.Gifs
import retrofit2.http.GET

interface MainApi {
    @GET(
//        "v1/gifs/search?" +
//                "api_key=XufPZyjyWSalJeAHofgkvDnCqEpxlCp5&" +
//                "q=smile+" +
//                "emoji&" +
//                "limit=25&" +
//                "offset=0&" +
//                "rating=g&" +
//                "lang=en&"
    "v1/gifs/trending?" +
            "api_key=XufPZyjyWSalJeAHofgkvDnCqEpxlCp5&" +
            "limit=25&" +
            "offset=0&" +
            "rating=g"
    )
    suspend fun getGifs(): Gifs
}