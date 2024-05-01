package com.example.remindme.data.repository

import com.example.remindme.data.datasource.Note
import com.example.remindme.data.datasource.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNotes() = noteDao.getAllNotes()

    fun getNotesByDateAdded() = noteDao.getNotesByDateAdded()

    fun getCompletedNotes(isCompleted: Boolean) = noteDao.getCompletedNotes(isCompleted)

}