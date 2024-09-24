package com.ismailmesutmujde.kotlindictionaryappvolley.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailmesutmujde.kotlindictionaryappvolley.R
import com.ismailmesutmujde.kotlindictionaryappvolley.adapter.WordsRecyclerViewAdapter
import com.ismailmesutmujde.kotlindictionaryappvolley.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlindictionaryappvolley.model.Words


class MainScreenActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var wordsList:ArrayList<Words>
    private lateinit var adapter: WordsRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Dictionary Application"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        wordsList = ArrayList()
        val w1 = Words(1, "Dog","Köpek")
        val w2 = Words(2, "Fish","Balık")
        val w3 = Words(3, "Table","Masa")

        wordsList.add(w1)
        wordsList.add(w2)
        wordsList.add(w3)

        adapter = WordsRecyclerViewAdapter(this, wordsList)
        bindingMainScreen.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Sent Search", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("As letters are entered", newText)
        return true
    }
}