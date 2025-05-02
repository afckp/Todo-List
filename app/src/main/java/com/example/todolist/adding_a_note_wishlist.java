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

public class adding_a_note_wishlist extends AppCompatActivity {

    private EditText editTextAddNoteWishList;
    private RadioButton radioButtonLowWishList;
    private RadioButton radioButtonMiddleWishList;
    private RadioButton radioButtonHighWishList;
    private Button buttonAddANoteWishList;
    private DataBaseWishList databaseWishList = DataBaseWishList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_anote_wishlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        buttonAddANoteWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String text = editTextAddNoteWishList.getText().toString().trim();
        int priority = getPriority();
        int id = databaseWishList.getNotes().size();
        NoteWishList noteWishList = new NoteWishList(id, text, priority);
        databaseWishList.add(noteWishList);

        finish();
    }

    private int getPriority() {
        int priority = 0;
        if (radioButtonLowWishList.isChecked()) {
            priority = 0;
        } else if (radioButtonMiddleWishList.isChecked()) {
            priority = 1;
        } else if (radioButtonHighWishList.isChecked()) {
            priority = 2;
        }
        return priority;
    }

    private void initViews() {
        editTextAddNoteWishList = findViewById(R.id.editTextAddNoteWishList);
        radioButtonLowWishList = findViewById(R.id.radioButtonLowWishList);
        radioButtonMiddleWishList = findViewById(R.id.radioButtonMiddleWishList);
        radioButtonHighWishList = findViewById(R.id.radioButtonHighWishList);
        buttonAddANoteWishList = findViewById(R.id.buttonAddANoteWishList);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, adding_a_note_wishlist.class);
        return intent;
    }
}