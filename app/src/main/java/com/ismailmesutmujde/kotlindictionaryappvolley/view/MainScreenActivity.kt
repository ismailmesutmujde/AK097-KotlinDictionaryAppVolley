package com.ismailmesutmujde.kotlindictionaryappvolley.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ismailmesutmujde.kotlindictionaryappvolley.R
import com.ismailmesutmujde.kotlindictionaryappvolley.adapter.WordsRecyclerViewAdapter
import com.ismailmesutmujde.kotlindictionaryappvolley.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlindictionaryappvolley.model.Words
import org.json.JSONObject


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

        /*
        wordsList = ArrayList()
        val w1 = Words(1, "Dog","Köpek")
        val w2 = Words(2, "Fish","Balık")
        val w3 = Words(3, "Table","Masa")

        wordsList.add(w1)
        wordsList.add(w2)
        wordsList.add(w3)

        adapter = WordsRecyclerViewAdapter(this, wordsList)
        bindingMainScreen.recyclerView.adapter = adapter

         */

        allWords()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchWord(query)
        Log.e("Sent Search", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchWord(newText)
        Log.e("As letters are entered", newText)
        return true
    }

    fun allWords() {
        val url = "http://kasimadalan.pe.hu/sozluk/tum_kelimeler.php"
        val request = StringRequest(Request.Method.GET, url, Response.Listener { answer->

            wordsList = ArrayList()
            try {
                val jsonObject = JSONObject(answer)
                val words = jsonObject.getJSONArray("kelimeler")

                for(i in 0 until words.length()) {
                    val w = words.getJSONObject(i)
                    val word = Words(w.getInt("kelime_id")
                        ,w.getString("ingilizce")
                        ,w.getString("turkce"))
                    wordsList.add(word)
                }

                adapter = WordsRecyclerViewAdapter(this@MainScreenActivity, wordsList)
                bindingMainScreen.recyclerView.adapter = adapter

            } catch (e:Exception) {
                e.printStackTrace()
            }
        }, Response.ErrorListener {  })
        Volley.newRequestQueue(this).add(request)
    }

    fun searchWord(searchWord:String) {
        val url = "http://kasimadalan.pe.hu/sozluk/kelime_ara.php"
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { answer->

            wordsList = ArrayList()

            try {
                val jsonObject = JSONObject(answer)
                val words = jsonObject.getJSONArray("kelimeler")

                for(i in 0 until words.length()) {
                    val w = words.getJSONObject(i)
                    val word = Words(w.getInt("kelime_id")
                        ,w.getString("ingilizce")
                        ,w.getString("turkce"))
                    wordsList.add(word)
                }

                adapter = WordsRecyclerViewAdapter(this@MainScreenActivity, wordsList)
                bindingMainScreen.recyclerView.adapter = adapter

            } catch (e:Exception) {
                e.printStackTrace()
            }
        }, Response.ErrorListener {  }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["ingilizce"] = searchWord
                return params
            }
        }
        Volley.newRequestQueue(this).add(request)
    }


}