package com.example.remindme.presentation.main_screen

import com.example.remindme.data.datasource.Note
import com.example.remindme.presentation.util.DISPLAY_TYPE


data class NotesScreenStates(
    val displayState: DISPLAY_TYPE = DISPLAY_TYPE.ALL,
    val currentNotesList: List<Note> = emptyList()
)
