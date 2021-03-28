package com.example.csci_442_stuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}