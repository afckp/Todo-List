package com.example.todolist;

import java.util.ArrayList;

public class DataBaseWork {
    private ArrayList<NoteWork> notesWork = new ArrayList<>();
    private static DataBaseWork instance = null;
    public static DataBaseWork getInstance() {
        if (instance == null) {
            instance = new DataBaseWork();
        }
        return instance;
    }

    public void add(NoteWork noteWork) {
        notesWork.add(noteWork);

    }

    public void remove(int id) {
        for (int i = 0; i < notesWork.size(); i++) {
            NoteWork noteWork = notesWork.get(i);
            if (noteWork.getId() == id) {
                notesWork.remove(noteWork);
            }
        }
    }

    public ArrayList<NoteWork> getNotes() {
        return new ArrayList<>(notesWork);
    }
}
