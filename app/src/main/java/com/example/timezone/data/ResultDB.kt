package com.example.timezone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ResultDataItem::class], version = 1, exportSchema = false)
abstract class ResultDB : RoomDatabase(){
    abstract fun getDao(): ResultDao
    companion object {
        @Volatile
        private var INSTANCE: ResultDB? = null
        fun getDatabase(context: Context): ResultDB {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDB::class.java,
                    "result_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}