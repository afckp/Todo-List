package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabaseAll extends RoomDatabase {

    private static NoteDatabaseAll instance = null;
    private static final String DB_All = "notesAll.db";

    public static NoteDatabaseAll getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application, NoteDatabaseAll.class,
                    DB_All
            ).build();
        }
        return instance;
    }

}
