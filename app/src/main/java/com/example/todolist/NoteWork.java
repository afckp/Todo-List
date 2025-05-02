package com.example.todolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notesWork")

public class NoteWork {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;
    public NoteWork(int id, String text, int priority) {
        this.id = id;
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
