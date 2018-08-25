package com.adetech.rooomkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private  val mInflater: LayoutInflater = LayoutInflater.from(context)
    private  var mWords: List<Word>? = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)

        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int = mWords?.size ?: 0

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current: Word? = mWords?.get(position)

        holder.wordItemView.text = current?.mWord
    }

    fun setWords(words: List<Word>?){
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

         val wordItemView: TextView = itemView.findViewById(R.id.textView)

    }

}
