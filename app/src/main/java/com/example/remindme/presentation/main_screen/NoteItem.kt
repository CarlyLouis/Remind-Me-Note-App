package com.example.remindme.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.remindme.data.datasource.Note
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
val date = LocalDate.now()
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(
    note: Note,
    onDeleteClick : ()-> Unit,
    onDoneClick : ()-> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .height(IntrinsicSize.Min)
                .wrapContentHeight(),
            verticalAlignment = Alignment.Top
        ) {
            Box(modifier = Modifier
                .size(width = 16.dp, height = 100.dp)
                .clip(RoundedCornerShape(50))
                .background(if (note.isCompleted) Color.Green else Color.Yellow)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Note created on: $date",
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    fontFamily = FontFamily.Cursive,
                    text = note.title+" :",
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    fontFamily = FontFamily.Cursive,
                    text = note.description,
                    modifier = Modifier.weight(1f),
                )
            }

        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if(!note.isCompleted){
                IconButton(onClick = onDoneClick) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = null)
                }
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = null)
            }
        }
    }
}