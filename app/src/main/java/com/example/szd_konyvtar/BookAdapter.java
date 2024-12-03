package com.example.szd_konyvtar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class    BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        super(context, R.layout.book, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book, parent, false);
        }

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        Book book = bookList.get(position);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView textViewPages = convertView.findViewById(R.id.textViewPages);
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());
        textViewPages.setText(String.valueOf(book.getPages()));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Könyv Törlése")
                        .setMessage("Biztosan kitörlöd ezt a könyvet?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                bookList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Könyv sikeresen törölve!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        return convertView;
    }
}
