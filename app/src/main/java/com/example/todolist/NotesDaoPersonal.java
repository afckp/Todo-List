package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDaoPersonal {

    @Query("SELECT * FROM notesPersonal")
    LiveData<List<NotePersonal>> getNotes();

    @Insert
    void add(NotePersonal notePersonal);

    @Query("DELETE FROM notesPersonal WHERE id = :id")
    void remove(int id);
}