package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {NoteWishList.class}, version = 1)
public abstract class NoteDatabaseWishList extends RoomDatabase {

    private static NoteDatabaseWishList instance = null;
    private static final String DB_WISHLIST = "notesWishList.db";

    public static NoteDatabaseWishList getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application, NoteDatabaseWishList.class,
                    DB_WISHLIST
            ).build();
        }
        return instance;
    }

}
