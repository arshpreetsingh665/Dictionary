package com.example.dictionary.apiinterface

import com.example.dictionary.modelclass.ModelClassItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2/entries/en/{word}")
    fun getResult(@Path("word") word: String): Call<List<ModelClassItem>>

}
