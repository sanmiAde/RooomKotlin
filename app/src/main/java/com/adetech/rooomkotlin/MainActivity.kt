package com.adetech.rooomkotlin

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val NEW_WORD_ACTIVITY_REQUEST_CODE: Int = 1
    private lateinit var mWordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)

        val adapter: WordListAdapter = setupRecyclerView()

        setupViewModel(adapter)

        fab.setOnClickListener { launchNewWordActivty() }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK -> {
                val word = Word(data.getStringExtra(EXTRA_REPLY))
                mWordViewModel.insert(word)

            }

           else -> Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()

        }
    }

    private fun setupViewModel(adapter: WordListAdapter) {
      mWordViewModel  = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.getAllWords().observe(this, Observer {
           word: List<Word>? -> adapter.setWords(word) })
    }

    private fun setupRecyclerView(): WordListAdapter {
        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        return adapter
    }

    private fun launchNewWordActivty(): Unit{
        val intent = Intent(MainActivity@this, NewWordActivity::class.java)
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)

    }
}
