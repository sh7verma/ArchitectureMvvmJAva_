package com.example.architecturemvvmjava.room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.architecturemvvmjava.data.Note

@Dao
interface DaoAccess {

    @get:Query("SELECT * FROM note_table ORDER BY priority DESC")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
}
