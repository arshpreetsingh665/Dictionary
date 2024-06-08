package com.example.dictionary.phoneticsadapter

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.PhoneticsItemViewBinding
import com.example.dictionary.modelclass.Phonetic

class PhoneticsAdapter(var context: Context, var list: List<Phonetic>) :
    RecyclerView.Adapter<PhoneticsAdapter.ViewHolder>() {

    class ViewHolder(var itemViewBinding: PhoneticsItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PhoneticsItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = list[position]
        holder.itemViewBinding.textviewPhonetics.text = list.text
        //  holder.itemViewBinding.audioPhonetics.=list.audio
        holder.itemViewBinding.audioPhonetics.setOnClickListener(View.OnClickListener {
            var mediaPlayer = MediaPlayer()
            try {
                mediaPlayer.setAudioAttributes(AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build())
                mediaPlayer.setDataSource(list.audio)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()

            }


        })


    }


}