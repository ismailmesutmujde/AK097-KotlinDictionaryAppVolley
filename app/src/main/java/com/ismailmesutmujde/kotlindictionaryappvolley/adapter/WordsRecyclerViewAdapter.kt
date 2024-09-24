package com.ismailmesutmujde.kotlindictionaryappvolley.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ismailmesutmujde.kotlindictionaryappvolley.R
import com.ismailmesutmujde.kotlindictionaryappvolley.model.Words
import com.ismailmesutmujde.kotlindictionaryappvolley.view.DetailScreenActivity

class WordsRecyclerViewAdapter(private val mContext : Context, private val wordsList : List<Words>)
    : RecyclerView.Adapter<WordsRecyclerViewAdapter.CardDesignHolder>() {
    inner class CardDesignHolder(view : View) : RecyclerView.ViewHolder(view) {
        var word_card: CardView
        var textViewEnglish: TextView
        var textViewTurkish: TextView

        init {
            word_card = view.findViewById(R.id.word_card)
            textViewEnglish = view.findViewById(R.id.textViewEnglish)
            textViewTurkish= view.findViewById(R.id.textViewTurkish)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val word = wordsList.get(position)

        holder.textViewEnglish.text = word.english
        holder.textViewTurkish.text = word.turkish

        holder.word_card.setOnClickListener {

            val intent = Intent(mContext, DetailScreenActivity::class.java)
            intent.putExtra("obj", word)
            mContext.startActivity(intent)

        }
    }
}