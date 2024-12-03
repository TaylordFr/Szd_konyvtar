package com.example.szd_konyvtar;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText pagesEditText;
    private Button addButton;
    private ListView booklistView;
    private ArrayList<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = titleEditText.getText().toString();
                String authorText = authorEditText.getText().toString();
                String pagesText = pagesEditText.getText().toString();

                if (titleText.isEmpty() || authorText.isEmpty() || pagesText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Töltsd ki az összes mezőt!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int pages = Integer.parseInt(pagesText);

                        if (pages < 50) {
                            Toast.makeText(getApplicationContext(), "Az oldalszám nem lehet kisebb mint 50!", Toast.LENGTH_SHORT).show();
                        } else {
                            bookList.add(new Book(titleText, authorText, pages));

                            bookAdapter.notifyDataSetChanged();

                            titleEditText.setText("");
                            authorEditText.setText("");
                            pagesEditText.setText("");
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Adj meg egy helyes oldalszámot!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        booklistView.setOnItemClickListener((parent, view, position, id) -> {
            Book selected = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("Title", selected.getTitle());
            intent.putExtra("Author", selected.getAuthor());
            intent.putExtra("Pages", selected.getPages());
            startActivity(intent);
        });


    }

    public void init() {
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        pagesEditText = findViewById(R.id.pagesEditText);
        addButton = findViewById(R.id.addButton);
        booklistView = findViewById(R.id.booklistView);
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        booklistView.setAdapter(bookAdapter);
        bookList.add(new Book("Metro 2033", "Dmitry Glukhovsky", 433));

    }
}
