package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDaoWishList {

    @Query("SELECT * FROM notesWishList")
    LiveData<List<NoteWishList>> getNotes();

    @Insert
    void add(NoteWishList noteWishList);

    @Query("DELETE FROM notesWishList WHERE id = :id")
    void remove(int id);
}
