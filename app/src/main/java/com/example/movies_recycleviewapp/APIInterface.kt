package com.example.movies_recycleviewapp

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("shows")
    fun getMovieData() : Call<MyData>

}