package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class adding_a_note extends AppCompatActivity {

    private EditText editTextAddNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMiddle;
    private RadioButton radioButtonHigh;
    private Button buttonAddANote;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_anote);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализируем единую базу данных
        appDatabase = AppDatabase.getInstance(getApplication());

        // Инициализируем компоненты
        initViews();

        // Назначаем обработчик для кнопки добавления задачи
        buttonAddANote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    // Инициализация виджетов
    private void initViews() {
        editTextAddNote = findViewById(R.id.editTextAddNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMiddle = findViewById(R.id.radioButtonMiddle);
        radioButtonHigh = findViewById(R.id.radioButtonHigh);
        buttonAddANote = findViewById(R.id.buttonAddANote);
    }

    // Сохранение новой задачи
    private void saveNote() {
        String text = editTextAddNote.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Введите текст задачи", Toast.LENGTH_SHORT).show();
            return;
        }

        int priority = getPriority();
        int selectedCategory = getIntent().getIntExtra("selected_category", Category.PERSONAL); // Получаем выбранную категорию

        // Создаем новую задачу
        Note note = new Note(text, priority, selectedCategory);

        // Добавляем задачу в базу данных
        appDatabase.notesDao().insert(note);

        // Закрываем активность
        finish();
    }

    // Получаем выбранный приоритет
    private int getPriority() {
        int priority = 0;
        if (radioButtonLow.isChecked()) {
            priority = 0;
        } else if (radioButtonMiddle.isChecked()) {
            priority = 1;
        } else if (radioButtonHigh.isChecked()) {
            priority = 2;
        }
        return priority;
    }

    // Получаем выбранную категорию задачи
    private int getSelectedCategory() {
        // Здесь реализуем логику определения категории (например, с помощью Spinner или RadioButtons)
        // Для примера предположим, что задача принадлежит к категории "Личное"
        return Category.PERSONAL;
    }

    // Создаем статический метод для запуска активности
    public static Intent newIntent(Context context) {
        return new Intent(context, adding_a_note.class);
    }
}