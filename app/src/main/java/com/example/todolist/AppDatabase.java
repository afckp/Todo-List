package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;
    private static final String DATABASE_NAME = "todolist.db";

    public static synchronized AppDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}