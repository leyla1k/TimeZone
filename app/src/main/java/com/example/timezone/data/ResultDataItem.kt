package com.example.timezone.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "results")
data class ResultDataItem(
    @PrimaryKey
    val id: UUID,
    val time: Int,
    val date: Long,
    val success: Boolean
)