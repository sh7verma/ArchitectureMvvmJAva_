package com.example.architecturemvvmjava.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import android.os.AsyncTask
import com.example.architecturemvvmjava.room.DaoAccess
import com.example.architecturemvvmjava.room.RoomDb
import com.example.architecturemvvmjava.data.Note

class NoteRepository(application: Application) {

    private val daoAccess: DaoAccess
    val allNotes: LiveData<List<Note>>

    init {
        val database = RoomDb.getInstance(application)
        daoAccess = database.noteDao()
        allNotes = daoAccess.allNotes
    }

    fun insert(note: Note) {
        InsertNoteAsyncTask(daoAccess).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(daoAccess).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(daoAccess).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(daoAccess).execute()
    }

    private class InsertNoteAsyncTask  constructor(private val daoAccess: DaoAccess) :
        AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            daoAccess.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask  constructor(private val daoAccess: DaoAccess) :
        AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            daoAccess.update(notes[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask  constructor(private val daoAccess: DaoAccess) :
        AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            daoAccess.delete(notes[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask  constructor(private val daoAccess: DaoAccess) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            daoAccess.deleteAllNotes()
            return null
        }
    }
}


