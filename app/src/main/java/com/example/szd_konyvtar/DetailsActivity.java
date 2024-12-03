package com.example.szd_konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private Button backButton;
    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewPages;
    private TextView textViewRandomYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }


    public void init(){
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPages = findViewById(R.id.textViewPages);
        textViewRandomYear = findViewById(R.id.textViewRandomYear);
        backButton = findViewById(R.id.backButton);
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String author = intent.getStringExtra("Author");
        int pages = intent.getIntExtra("Pages", 0);

        textViewTitle.setText("Cím: " + title);
        textViewAuthor.setText("Szerző: " + author);
        textViewPages.setText("Oldalszám: " + pages);

        Random random = new Random();
        int randomYear = 1888 + random.nextInt(123);
        textViewRandomYear.setText("Kiadási év: " + randomYear);
        backButton.setOnClickListener(v -> finish());
    }
}