package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
    private DataBase database = DataBase.getInstance();

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
        initViews();

        buttonAddANote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initViews() {
        editTextAddNote = findViewById(R.id.editTextAddNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMiddle = findViewById(R.id.radioButtonMiddle);
        radioButtonHigh = findViewById(R.id.radioButtonHigh);
        buttonAddANote = findViewById(R.id.buttonAddANote);
    }

    private void saveNote() {
        String text = editTextAddNote.getText().toString().trim();
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id, text, priority);
        database.add(note);

        finish();
    }

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

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, adding_a_note.class);
        return intent;
    }
}