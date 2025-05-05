package com.example.todolist;

import java.util.ArrayList;

public class DataBasePersonal {
    private ArrayList<NotePersonal> notesPersonal = new ArrayList<>();
    private static DataBasePersonal instance = null;
    public static DataBasePersonal getInstance() {
        if (instance == null) {
            instance = new DataBasePersonal();
        }
        return instance;
    }

    public void add(NotePersonal notePersonal) {
        notesPersonal.add(notePersonal);


    }

    public void remove(int id) {
        for (int i = 0; i < notesPersonal.size(); i++) {
            NotePersonal notePersonal = notesPersonal.get(i);
            if (notePersonal.getId() == id) {
                notesPersonal.remove(notePersonal);
            }
        }
    }

    public ArrayList<NotePersonal> getNotes() {
        return new ArrayList<>(notesPersonal);
    }
}
