package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes WHERE category=:category OR category=0 ORDER BY id DESC")
    LiveData<List<Note>> getTasksByCategory(int category);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    LiveData<List<Note>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Delete
    void delete(Note note);
}
