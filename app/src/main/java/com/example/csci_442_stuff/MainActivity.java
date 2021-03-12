package com.example.csci_442_stuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toastButton = findViewById(R.id.toastButton);

    }
    public void onClick(View view){
        if(view.getId() == R.id.toastButton){
            Toast.makeText(this, "Hello CSCI 442", Toast.LENGTH_LONG).show();
        }


    }
}