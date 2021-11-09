package com.example.review.Retrofit

class FathersAPIModel : ArrayList<FathersAPIModel.FathersAPIModelItem>(){
    data class FathersAPIModelItem(
        val location: String,
        val name: String,
        val pk: Int
    )
}