package com.example.remindme.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remindme.data.datasource.Note
import com.example.remindme.presentation.util.DISPLAY_TYPE
import com.example.remindme.presentation.ui.NoteViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteMainScreen(
    viewModel: NoteViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState
    var expanded by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Remind Me",
                    fontWeight = FontWeight.ExtraBold

                )},
                actions = {
                    IconButton(onClick = {expanded = !expanded }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
                        DropdownMenuItem(
                            text = { Text(text = "All") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.ALL)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Ongoing") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.IN_PROGRESS)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Completed") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.COMPLETED)
                                expanded = false
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(15.dp),
                contentPadding = PaddingValues(8.dp)
            ){
                items(uiState.value.currentNotesList){item: Note ->
                    NoteItem(
                        note = item,
                        onDoneClick = {
                            viewModel.update(
                                Note(

                                    item.title,
                                    item.description,
                                    true,
                                    null,
                                    item.id))
                        },
                        onDeleteClick = {
                            viewModel.delete(item)
                        }
                    )
                }
            }
        }

        if(showDialog){
            UpdateDialog(
                viewModel = viewModel,
                onDismissRequest = {
                    showDialog = false
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyScreenPreview(){
    NoteMainScreen()
}