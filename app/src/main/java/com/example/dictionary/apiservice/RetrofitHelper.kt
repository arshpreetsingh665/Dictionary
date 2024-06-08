package com.example.dictionary.apiservice

import com.example.dictionary.apiinterface.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        fun getInstance(): ApiService {
            var loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            var httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)


            return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                .build().create(ApiService::class.java)
        }
    }
}