package com.example.timezone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ResultDao {

    @Query("SELECT * FROM results")
    fun getAllResultFlow(): Flow<List<ResultDataItem>>

    @Insert
    suspend fun insertNewResult(resultDataItem: ResultDataItem)

    @Delete
    suspend fun deleteResult(resultDataItem: ResultDataItem)
}