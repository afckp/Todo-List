package com.example.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;               // Текст задачи
    private int priority;              // Уровень важности (0 — низкая, 1 — средняя, 2 — высокая)
    private int category;              // Категория задачи (используется константы из класса Category)

    // Конструктор для задач с авто-инкрементом ID
    public Note(String text, int priority, int category) {
        this.text = text;
        this.priority = priority;
        this.category = category;
    }

    // Игнорируемый конструктор для создания задач без ID (Room сгенерирует ID автоматически)
    @Ignore
    public Note(int id, String text, int priority, int category) {
        this.id = id;
        this.text = text;
        this.priority = priority;
        this.category = category;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}