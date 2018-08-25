package com.adetech.rooomkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_word.*

import kotlinx.android.synthetic.main.activity_new_word2.*

const val EXTRA_REPLY: String = "com.adetech.RooomKotlin.wordlistsql.REPLY"


class NewWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        setSupportActionBar(toolbar)


        button_save.setOnClickListener { saveWord() }
    }

    private fun saveWord(): Unit{
        val replyIntent = Intent()
        val isTextEmpty : Boolean = TextUtils.isEmpty(edit_word.text)

        when {
            !isTextEmpty -> {
                replyIntent.putExtra(EXTRA_REPLY, edit_word.text.toString())
                when (parent) {
                    null -> setResult(Activity.RESULT_OK, replyIntent)
                    else -> parent.setResult(Activity.RESULT_OK, replyIntent)
                }

            }
            else -> setResult(Activity.RESULT_CANCELED, replyIntent)
        }

        finish()
    }

}

