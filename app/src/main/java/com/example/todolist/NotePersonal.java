package com.example.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notesPersonal")

public class NotePersonal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;


    @Ignore
    public NotePersonal(String text, int priority) {
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
