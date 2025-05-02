package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {NotePersonal.class}, version = 1)
public abstract class NoteDatabasePersonal extends RoomDatabase {

    private static NoteDatabasePersonal instance = null;
    private static final String DB_PERSONAL = "notesPersonal.db";

    public static NoteDatabasePersonal getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application, NoteDatabasePersonal.class,
                    DB_PERSONAL
            ).build();
        }
        return instance;
    }

}
