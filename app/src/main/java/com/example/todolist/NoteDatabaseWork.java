package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {NoteWork.class}, version = 1)
public abstract class NoteDatabaseWork extends RoomDatabase {

    private static NoteDatabaseWork instance = null;
    private static final String DB_WORK = "notesWork.db";

    public static NoteDatabaseWork getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application, NoteDatabaseWork.class,
                    DB_WORK
            ).build();
        }
        return instance;
    }

    public abstract NotesDaoWork notesDaoWork();

}
