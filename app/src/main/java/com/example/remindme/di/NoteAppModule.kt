package com.example.remindme.di

import android.content.Context
import androidx.room.Room
import com.example.remindme.data.datasource.NoteDao
import com.example.remindme.data.datasource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    @Provides
    fun provideDao (noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()
}