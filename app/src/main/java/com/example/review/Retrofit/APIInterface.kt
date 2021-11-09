package com.example.review.Retrofit

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("/test/?format=json")
    fun getFathersData(): Call<List<FathersAPIModel.FathersAPIModelItem>>
}