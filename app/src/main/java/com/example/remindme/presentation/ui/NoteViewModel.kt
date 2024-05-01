package com.example.remindme.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindme.data.datasource.Note
import com.example.remindme.data.repository.NoteRepository
import com.example.remindme.presentation.main_screen.NotesScreenStates
import com.example.remindme.presentation.util.DISPLAY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository): ViewModel() {

    val uiState = mutableStateOf(NotesScreenStates())


    init {
        getNotes()
    }

    fun getNotes(){
        when(uiState.value.displayState){
            DISPLAY_TYPE.ALL-> {
                viewModelScope.launch {
                    repository.getAllNotes().collect {list->
                        uiState.value = uiState.value.copy(
                            currentNotesList = list
                        )
                    }
                }
            }

            DISPLAY_TYPE.IN_PROGRESS-> {
                viewModelScope.launch {
                    repository.getCompletedNotes(false).collect {list->
                        uiState.value = uiState.value.copy(
                            currentNotesList = list
                        )
                    }
                }
             }

            DISPLAY_TYPE.COMPLETED-> {
                viewModelScope.launch {
                    repository.getCompletedNotes(true).collect {list->
                        uiState.value = uiState.value.copy(
                            currentNotesList = list
                        )
                    }
                }
            }


        }
    }

    fun insert(note: Note){
        viewModelScope.launch {
            repository.insertNote(note)
        }

    }

    fun delete(note: Note){
        viewModelScope.launch {
            repository.deleteNote(note)
        }

    }

    fun update(note: Note){
        viewModelScope.launch {
            repository.updateNote(note)
        }

    }

    fun switchDisplayType(dt: DISPLAY_TYPE){
        uiState.value = uiState.value.copy(
            displayState = dt
        )
        getNotes()
    }
}