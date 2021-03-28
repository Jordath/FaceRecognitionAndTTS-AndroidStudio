package com.example.csci_442_stuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    List<Face> fac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        InputStream stream = getResources().openRawResource(R.raw.image02);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        InputImage image = InputImage.fromBitmap(bitmap, 0);

        FaceDetector detector = FaceDetection.getClient();

        Task<List<Face>> result = detector.process(image).addOnSuccessListener(
                new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        fac = faces;
                        if(fac == null){
                            Log.v("**draw***", "main2 FAXW is null");
                        }
                        FaceView overlay = (FaceView) findViewById(R.id.faceView);
                        overlay.setContent(bitmap, fac);
                        tv.setText(faces.size() + " Faces Seen");
                    }
                }
        ).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }
        );
        /*
        Button toastButton = findViewById(R.id.toastButton);
        toastButton.setOnClickListener(this);
        Button activityButton = findViewById(R.id.newActivityButton);
        activityButton.setOnClickListener(this);
        Button talkButton = findViewById(R.id.talkButton);
        talkButton.setOnClickListener(this);
        */


    }
    public void onClick(View view){
        /*
        if(view.getId() == R.id.toastButton){
            Toast.makeText(this, "Hello CSCI 442", Toast.LENGTH_LONG).show();
        }
        if(view.getId() == R.id.newActivityButton){
            Toast.makeText(this, "Start New Activity", Toast.LENGTH_LONG).show();
            startSecondActivity();

        }
        if(view.getId() == R.id.talkButton){
            Toast.makeText(this, "Start Talking", Toast.LENGTH_LONG).show();
            startTalkActivity();

        }

         */


    }
    public void startSecondActivity(){
        Intent secondActivity = new Intent(this, SecondActivity.class);
        startActivity(secondActivity);

    }
    public void startTalkActivity(){
        Intent talkActivity = new Intent(this, TalkActivity.class);
        startActivity(talkActivity);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}