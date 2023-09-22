package com.example.timezone.data

import java.util.Date
import java.util.UUID


data class Result(
    val id: UUID,
    val time: Int,
    val date: Date,
    val success: Boolean
)