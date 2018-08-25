package com.adetech.rooomkotlin

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetisedWords(): LiveData<List<Word>>

    @Insert
    fun insert(word: Word): Unit

    @Query("DELETE FROM word_table")
    fun deleteAll(): Unit
}