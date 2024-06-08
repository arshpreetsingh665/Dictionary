package com.example.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dictionary.modelclass.ModelClassItem
import com.example.dictionary.repo.Repo
import retrofit2.Call

class ViewModel:ViewModel() {
    val repo=Repo()
    suspend fun getResults(word:String): Call<List<ModelClassItem>> {
        return repo.getResults(word)
    }

}