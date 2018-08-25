package com.adetech.rooomkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository

    private val mAllWords: LiveData<List<Word>>

    init{
        mRepository = WordRepository(application)
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> = mAllWords

    fun insert(word: Word): Unit{
        mRepository.insert(word)
    }

}