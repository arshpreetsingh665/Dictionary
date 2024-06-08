package com.example.dictionary.meaningadpter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.MeaningItemViewBinding
import com.example.dictionary.definationadapter.DefinitionAdapter
import com.example.dictionary.modelclass.Meaning

class MeaningAdapter(var context: Context, var list: List<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.ViewHolder>() {
    class ViewHolder(var itemViewBinding: MeaningItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MeaningItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.itemViewBinding.textviewPartsOfSpeech.text = item.partOfSpeech
        holder.itemViewBinding.recyclerViewForDefinitions.setHasFixedSize(true)
        holder.itemViewBinding.recyclerViewForDefinitions.layoutManager =
            LinearLayoutManager(context)
        holder.itemViewBinding.recyclerViewForDefinitions.adapter = DefinitionAdapter(
            context,
            item.definitions
        )

    }
}