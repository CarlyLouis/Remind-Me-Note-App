package com.example.remindme.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val dateAdded: Long?,
    @PrimaryKey(true)
    val id: Int?
)



