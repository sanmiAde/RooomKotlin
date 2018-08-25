package com.adetech.rooomkotlin

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {

    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>

    init {
        val db: WordRoomDatabase= WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAlphabetisedWords()
    }

    fun getAllWords(): LiveData<List<Word>> = mAllWords

    fun insert(word: Word) : Unit {
        InsetAsyncTask(mWordDao).execute(word)
    }

    class InsetAsyncTask(private val mWordDao: WordDao) : AsyncTask<Word, Unit, Unit>() {

        override fun doInBackground(vararg word: Word): Unit? {
           mWordDao.insert(word = word[0])
            return null
        }


    }
}