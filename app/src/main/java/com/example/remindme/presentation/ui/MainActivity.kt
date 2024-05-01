package com.example.remindme.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.remindme.presentation.main_screen.NoteMainScreen
import com.example.remindme.presentation.ui.theme.RemindMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindMeTheme {
               NoteMainScreen()
            }
        }
    }
}

/*
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindMeTheme {
                NoteMainScreen()
            }
        }
    }
}*/
