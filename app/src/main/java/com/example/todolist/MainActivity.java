package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonAll;
    private Button buttonWork;
    private Button buttonPersonal;
    private Button buttonWishList;
    private Button buttonCalendar;


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
        initViews();

        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ActivityAll.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        buttonPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = personal.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        buttonWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Work.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        buttonWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WishList.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }
    private void initViews() {
        buttonAll = findViewById(R.id.buttonAll);
        buttonWork = findViewById(R.id.buttonWork);
        buttonPersonal = findViewById(R.id.buttonPersonal);
        buttonWishList = findViewById(R.id.buttonWishList);
        buttonCalendar = findViewById(R.id.buttonCalendar);
    }


}