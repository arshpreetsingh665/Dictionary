package com.example.dictionary.repo

import com.example.dictionary.apiservice.RetrofitHelper
import com.example.dictionary.modelclass.ModelClassItem
import retrofit2.Call

class Repo {
   suspend fun getResults(word:String): Call<List<ModelClassItem>> {
   return   RetrofitHelper.getInstance().getResult(word)
    }
}