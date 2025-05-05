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


public class adding_a_note_personal extends AppCompatActivity {

    private EditText editTextAddNotePersonal;
    private RadioButton radioButtonLowPersonal;
    private RadioButton radioButtonMiddlePersonal;
    private RadioButton radioButtonHighPersonal;
    private Button buttonAddANotePersonal;
    private NoteDatabasePersonal noteDatabasePersonal;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_anote_personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        noteDatabasePersonal = NoteDatabasePersonal.getInstance(getApplication());
        initViews();

        buttonAddANotePersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String text = editTextAddNotePersonal.getText().toString().trim();
        int priority = getPriority();
        NotePersonal notePersonal = new NotePersonal(text, priority);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDatabasePersonal.notesDaoPersonal().add(notePersonal);
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
        if (radioButtonLowPersonal.isChecked()) {
            priority = 0;
        } else if (radioButtonMiddlePersonal.isChecked()) {
            priority = 1;
        } else if (radioButtonHighPersonal.isChecked()) {
            priority = 2;
        }
        return priority;
    }

    private void initViews() {
        editTextAddNotePersonal = findViewById(R.id.editTextAddNotePersonal);
        radioButtonLowPersonal = findViewById(R.id.radioButtonLowPersonal);
        radioButtonMiddlePersonal = findViewById(R.id.radioButtonMiddlePersonal);
        radioButtonHighPersonal = findViewById(R.id.radioButtonHighPersonal);
        buttonAddANotePersonal = findViewById(R.id.buttonAddANotePersonal);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, adding_a_note_personal.class);
        return intent;
    }
}