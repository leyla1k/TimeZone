package com.example.timezone.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ResultRepository(val dao: ResultDao) {


    fun getAllResultFlow(): Flow<List<Result>> {
        val list = dao.getAllResultFlow()
        return list.map {
            it.map { uit ->
                fromResultDataItemToResult(uit)
            }
        }

    }


    suspend fun insertNewResult(resultDataItem: ResultDataItem) {
        withContext(Dispatchers.IO) {
            dao.insertNewResult(resultDataItem)
        }
    }


    suspend fun deleteResult(resultDataItem: ResultDataItem) {
        withContext(Dispatchers.IO) {
            dao.deleteResult(resultDataItem)
        }
    }
}