package com.adetech.rooomkotlin

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Word::class], version = 1)
 abstract class  WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class  PopulateDbAsync internal constructor(db: WordRoomDatabase) : AsyncTask<Unit,Unit, Unit>() {

        private val mDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Unit): Unit? {
            mDao.deleteAll()
            val word = Word("Hello word")
            mDao.insert(word)

           return null
        }
    }

    companion object {

        private var INSTANCE: WordRoomDatabase? = null

        @Synchronized
         fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null){

                INSTANCE = Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database" ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
               PopulateDbAsync(INSTANCE!!).execute()
            }
        }

    }


}

