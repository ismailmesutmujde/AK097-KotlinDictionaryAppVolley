package com.ismailmesutmujde.kotlindictionaryappvolley.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlindictionaryappvolley.databinding.ActivityDetailScreenBinding
import com.ismailmesutmujde.kotlindictionaryappvolley.model.Words


class DetailScreenActivity : AppCompatActivity() {

    private lateinit var bindingDetailScreen : ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailScreen = ActivityDetailScreenBinding.inflate(layoutInflater)
        val view = bindingDetailScreen.root
        setContentView(view)

        val word = intent.getSerializableExtra("obj") as Words

        bindingDetailScreen.textViewEnglish2.text = word.english
        bindingDetailScreen.textViewTurkish2.text = word.turkish

    }
}