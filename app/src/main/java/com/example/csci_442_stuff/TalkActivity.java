package com.example.csci_442_stuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TalkActivity extends AppCompatActivity implements View.OnClickListener {

    private TTS tts;
    String msg;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);

        Button talkButton = findViewById(R.id.talkButton);
        talkButton.setOnClickListener(this);

        et = findViewById(R.id.talkText);
        et.setText("Hello");
        tts = TTS.getInstance(this);
        tts.start();
    }

    public void onClick(View view) {
        if(view.getId() == R.id.talkButton){
            Bundle b = new Bundle();
            String text = et.getText().toString();
            b.putString("LM", text);
            if(tts.handler != null){
                Message msg = tts.handler.obtainMessage(0);
                msg.setData(b);
                tts.handler.sendMessage(msg);
            }
        }
        if(view.getId() == R.id.returnButton) {
            finish();
        }
    }
}