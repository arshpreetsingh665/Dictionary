package com.example.dictionary.definationadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.DefinitionItemViewBinding
import com.example.dictionary.modelclass.Definition

class DefinitionAdapter(var context: Context, var list: List<Definition>) :
    RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {
    class ViewHolder(var itemViewBinding: DefinitionItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DefinitionItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.itemViewBinding.textviewDefinition.text = item.definition
        holder.itemViewBinding.textviewExample.text = item.example
        holder.itemViewBinding.textviewAntonym.text = item.antonyms.toString()
        holder.itemViewBinding.textviewSynonym.text = item.antonyms.toString()

    }
}