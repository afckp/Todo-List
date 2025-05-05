package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapterWishList extends RecyclerView.Adapter<NotesAdapterWishList.NotesViewHolder> {

    private List<NoteWishList> notesWishList = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener;

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public List<NoteWishList> getNotes() {
        return new ArrayList<>(notesWishList);
    }

    public void setNotesWishList(List<NoteWishList> notesWishList) {
        this.notesWishList = notesWishList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        NoteWishList noteWishList = (NoteWishList) notesWishList.get(position);
        viewHolder.textViewNote.setText(noteWishList.getText());

        int colorResId = 0;
        switch (noteWishList.getPriority()) {
            case 0:
                colorResId = R.color.low;
                break;
            case 1:
                colorResId = R.color.middle;
                break;
            case 2:
                colorResId = R.color.high;
                break;
        }
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);
        viewHolder.textViewNote.setBackgroundColor(color);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClickListener != null) {
                    onNoteClickListener.onNoteClick(noteWishList);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesWishList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNote;
        public  NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }

    interface OnNoteClickListener {
        void onNoteClick(NoteWishList noteWishList);
    }

}
