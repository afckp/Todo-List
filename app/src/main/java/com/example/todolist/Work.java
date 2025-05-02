package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Work extends AppCompatActivity {
    private FloatingActionButton floatingActionButtonWork;

    private RecyclerView recyclerViewNotesWork;
    private NotesAdapterWork notesAdapterWork;
    private DataBaseWork databaseWork = DataBaseWork.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        notesAdapterWork = new NotesAdapterWork();
        notesAdapterWork.setOnNoteClickListener(new NotesAdapterWork.OnNoteClickListener() {
            @Override
            public void onNoteClick(NoteWork noteWork) {
                Intent intent = adding_a_note_work.newIntent(Work.this);
                startActivity(intent);
            }
        });
        recyclerViewNotesWork.setAdapter(notesAdapterWork);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NoteWork noteWork = (NoteWork) notesAdapterWork.getNotes().get(position);
                databaseWork.remove(noteWork.getId());
                showNotes();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotesWork);

        floatingActionButtonWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = adding_a_note_work.newIntent(Work.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews() {
        recyclerViewNotesWork = findViewById(R.id.recyclerViewNotesWork);
        floatingActionButtonWork = findViewById(R.id.floatingActionButtonWork);
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, Work.class);
        return intent;
    }

    private void showNotes() {
        notesAdapterWork.setNotesWork(databaseWork.getNotes());
    }
}
