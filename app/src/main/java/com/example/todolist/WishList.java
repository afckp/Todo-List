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

public class WishList extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonWishList;

    private RecyclerView recyclerViewNotesWishList;
    private NotesAdapterWishList notesAdapterWishList;
    private DataBaseWishList databaseWishList = DataBaseWishList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wish_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        notesAdapterWishList = new NotesAdapterWishList();
        notesAdapterWishList.setOnNoteClickListener(new NotesAdapterWishList.OnNoteClickListener() {
            @Override
            public void onNoteClick(NoteWishList noteWishList) {
                Intent intent = adding_a_note_wishlist.newIntent(WishList.this);
                startActivity(intent);
            }
        });
        recyclerViewNotesWishList.setAdapter(notesAdapterWishList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NoteWishList noteWishList = (NoteWishList) notesAdapterWishList.getNotes().get(position);
                databaseWishList.remove(noteWishList.getId());
                showNotes();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotesWishList);

        floatingActionButtonWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = adding_a_note.newIntent(WishList.this);
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
        floatingActionButtonWishList = findViewById(R.id.floatingActionButtonWishList);
        recyclerViewNotesWishList = findViewById(R.id.recyclerViewNotesWishList);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, WishList.class);
        return intent;
    }

    private void showNotes() {
        notesAdapterWishList.setNotesWishList(databaseWishList.getNotes());
    }
}