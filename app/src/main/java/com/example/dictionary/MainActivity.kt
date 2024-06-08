package com.example.dictionary

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.meaningadpter.MeaningAdapter
import com.example.dictionary.modelclass.ModelClassItem
import com.example.dictionary.phoneticsadapter.PhoneticsAdapter
import com.example.dictionary.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        val progressDialog = ProgressDialog(this)

        mainBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    progressDialog.setTitle("Fetching Response for $query")
                    progressDialog.show()
                    progressDialog.dismiss()
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.getResults(query)
                            .enqueue(object : Callback<List<ModelClassItem>> {
                                override fun onResponse(
                                    call: Call<List<ModelClassItem>>,
                                    response: Response<List<ModelClassItem>>
                                ) {
                                    val modelClassList = response.body()
                                    if (!modelClassList.isNullOrEmpty()) {
                                        val modelClass = modelClassList[0]
                                        Log.e("Success", query.toString())

                                        mainBinding.recyclerPhonetics.layoutManager =
                                            LinearLayoutManager(this@MainActivity)
                                        val phoneticsAdapter =
                                            PhoneticsAdapter(
                                                this@MainActivity,
                                                modelClass.phonetics!!
                                            )
                                        mainBinding.recyclerPhonetics.adapter = phoneticsAdapter

                                        // Set up Meanings RecyclerView
                                        mainBinding.recyclerMeanings.layoutManager =
                                            LinearLayoutManager(this@MainActivity)
                                        val meaningAdapter =
                                            MeaningAdapter(this@MainActivity, modelClass.meanings!!)
                                        mainBinding.recyclerMeanings.adapter = meaningAdapter
                                    }
                                }

                                override fun onFailure(
                                    call: Call<List<ModelClassItem>>,
                                    t: Throwable
                                ) {
                                    progressDialog.dismiss()
                                    Log.e("Failure", "API request failed: ${t.message}")
                                    // Handle the failure case here, such as displaying an error message to the user
                                    Toast.makeText(
                                        this@MainActivity,
                                        "API request failed: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })}
                    }

                    return true
                }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }}

