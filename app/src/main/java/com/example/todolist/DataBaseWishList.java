package com.example.todolist;

import java.util.ArrayList;

public class DataBaseWishList {
    private ArrayList<NoteWishList> notesWishList = new ArrayList<>();
    private static DataBaseWishList instance = null;
    public static DataBaseWishList getInstance() {
        if (instance == null) {
            instance = new DataBaseWishList();
        }
        return instance;
    }

    public void add(NoteWishList noteWishList) {
        notesWishList.add(noteWishList);

    }

    public void remove(int id) {
        for (int i = 0; i < notesWishList.size(); i++) {
            NoteWishList noteWishList = notesWishList.get(i);
            if (noteWishList.getId() == id) {
                notesWishList.remove(noteWishList);
            }
        }
    }

    public ArrayList<NoteWishList> getNotes() {
        return new ArrayList<>(notesWishList);
    }
}
