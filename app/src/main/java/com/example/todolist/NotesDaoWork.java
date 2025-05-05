package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDaoWork {

    @Query("SELECT * FROM notesWork")
    LiveData<List<NoteWork>> getNotes();

    @Insert
    void add(NoteWork noteWork);

    @Query("DELETE FROM notesWork WHERE id = :id")
    void remove(int id);
}