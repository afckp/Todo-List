package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class personal extends AppCompatActivity {

    private RecyclerView recyclerViewNotesPersonal;
    private FloatingActionButton buttonAddNote;
    private NotesAdapterPersonal notesAdapterPersonal;
    private NoteDatabasePersonal noteDatabasePersonal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        noteDatabasePersonal = NoteDatabasePersonal.getInstance(getApplication());

        initViews();

        notesAdapterPersonal = new NotesAdapterPersonal();
        notesAdapterPersonal.setOnNoteClickListener(new NotesAdapterPersonal.OnNoteClickListener() {
            @Override
            public void onNoteClick(NotePersonal notePersonal) {
                Intent intent = adding_a_note_personal.newIntent(personal.this);
                startActivity(intent);
            }
        });
        recyclerViewNotesPersonal.setAdapter(notesAdapterPersonal);

        noteDatabasePersonal.notesDaoPersonal().getNotes().observe(this, new Observer<List<NotePersonal>>() {
            @Override
            public void onChanged(List<NotePersonal> notesPersonal) {
                notesAdapterPersonal.setNotesPersonal(notesPersonal);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NotePersonal notePersonal = (NotePersonal) notesAdapterPersonal.getNotes().get(position);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        noteDatabasePersonal.notesDaoPersonal().remove(notePersonal.getId());
                    }
                });
                thread.start();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewNotesPersonal);


        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = adding_a_note_personal.newIntent(personal.this);
                startActivity(intent);
            }
        });
    }
    private void initViews() {
        recyclerViewNotesPersonal = findViewById(R.id.recyclerViewNotesPersonal);
        buttonAddNote = findViewById(R.id.buttonAddNote);

    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, personal.class);
        return intent;
    }
}