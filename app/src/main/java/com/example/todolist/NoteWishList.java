package com.example.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notesWishList")

public class NoteWishList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;
    public NoteWishList(int id, String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    @Ignore
    public NoteWishList(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public int getPriority() {
        return priority;
    }
}
