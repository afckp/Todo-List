package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class adding_a_note_work extends AppCompatActivity {

    private EditText editTextAddNoteWork;
    private RadioButton radioButtonLowWork;
    private RadioButton radioButtonMiddleWork;
    private RadioButton radioButtonHighWork;
    private Button buttonAddANoteWork;
    private NoteDatabaseWork noteDatabaseWork;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_anote_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        noteDatabaseWork = NoteDatabaseWork.getInstance(getApplication());
        initViews();

        buttonAddANoteWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String text = editTextAddNoteWork.getText().toString().trim();
        int priority = getPriority();
        NoteWork noteWork = new NoteWork(text, priority);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDatabaseWork.notesDaoWork().add(noteWork);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        finish();
                    }
                });
            }
        });
        thread.start();
    }

    private int getPriority() {
        int priority = 0;
        if (radioButtonLowWork.isChecked()) {
            priority = 0;
        } else if (radioButtonMiddleWork.isChecked()) {
            priority = 1;
        } else if (radioButtonHighWork.isChecked()) {
            priority = 2;
        }
        return priority;
    }

    private void initViews() {
        editTextAddNoteWork = findViewById(R.id.editTextAddNoteWork);
        radioButtonLowWork = findViewById(R.id.radioButtonLowWork);
        radioButtonMiddleWork = findViewById(R.id.radioButtonMiddleWork);
        radioButtonHighWork = findViewById(R.id.radioButtonHighWork);
        buttonAddANoteWork = findViewById(R.id.buttonAddANoteWork);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, adding_a_note_work.class);
        return intent;
    }
}