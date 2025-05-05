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

public class Work extends AppCompatActivity {
    private FloatingActionButton floatingActionButtonWork;

    private RecyclerView recyclerViewNotesWork;
    private NotesAdapterWork notesAdapterWork;
    private NoteDatabaseWork noteDatabaseWork;


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

        noteDatabaseWork = NoteDatabaseWork.getInstance(getApplication());

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

        noteDatabaseWork.notesDaoWork().getNotes().observe(this, new Observer<List<NoteWork>>() {
            @Override
            public void onChanged(List<NoteWork> notesWork) {
                notesAdapterWork.setNotesWork(notesWork);
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
                NoteWork noteWork = (NoteWork) notesAdapterWork.getNotes().get(position);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        noteDatabaseWork.notesDaoWork().remove(noteWork.getId());
                        }
                });
                thread.start();
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

    private void initViews() {
        recyclerViewNotesWork = findViewById(R.id.recyclerViewNotesWork);
        floatingActionButtonWork = findViewById(R.id.floatingActionButtonWork);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, Work.class);
        return intent;
    }
}
