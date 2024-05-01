package com.example.remindme.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note:Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY isCompleted = :isCompleted")
    fun getCompletedNotes(isCompleted:Boolean): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY dateAdded ASC")
    fun getNotesByDateAdded(): Flow<List<Note>>
}