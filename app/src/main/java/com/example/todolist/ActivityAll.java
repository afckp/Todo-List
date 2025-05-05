package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityAll extends AppCompatActivity {

    private RecyclerView recyclerViewNotesAll;
    private FloatingActionButton floatingActionButtonAll;
    private NotesAdapter notesAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализируем нашу единую базу данных
        appDatabase = AppDatabase.getInstance(getApplication());

        // Инициализируем компоненты
        initViews();

        // Создаем адаптер и назначаем его RecylerView
        notesAdapter = new NotesAdapter();
        notesAdapter.setOnNoteClickListener((note) -> {
            Intent intent = adding_a_note.newIntent(ActivityAll.this);
            startActivity(intent);
        });
        recyclerViewNotesAll.setAdapter(notesAdapter);

        // Наблюдаем за изменением задач и обновляем список
        appDatabase.notesDao().getAllTasks().observe(this, notes -> {
            notesAdapter.setNotes(notes);
        });

        // Настраиваем swipe-to-delete (свайп вправо-влево для удаления задачи)
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);
                appDatabase.notesDao().delete(note);
                Toast.makeText(ActivityAll.this, "Задача удалена", Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotesAll);

        // Обработчик кнопки добавления новой задачи
        floatingActionButtonAll.setOnClickListener(v -> {
            Intent intent = adding_a_note.newIntent(ActivityAll.this);
            startActivity(intent);
        });
    }

    // Инициализация виджетов
    private void initViews() {
        recyclerViewNotesAll = findViewById(R.id.recyclerViewNotesAll);
        floatingActionButtonAll = findViewById(R.id.floatingActionButtonAll);
    }

    // Создаем статический метод для запуска активности
    public static Intent newIntent(Context context) {
        return new Intent(context, ActivityAll.class);
    }
}