package com.pahadi.composenewsapp.database.entity
import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo("sourceId")
    val id: String?,

    @ColumnInfo("sourceName")
    val name: String?
)