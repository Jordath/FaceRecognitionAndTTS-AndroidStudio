package com.example.csci_442_stuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TTS tts;
    String msg;
    EditText et;

    TextView tv;
    List<Face> fac;
    List<Face> emptyFaces = null;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        tts = TTS.getInstance(this);
        tts.start();

        ImageView tv1;

        InputStream stream = getResources().openRawResource(R.raw.image01);
        bitmap = BitmapFactory.decodeStream(stream);
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

                        //overlay.setContent(bitmap, fac);
                        msg = faces.size() + " Faces Seen";
                        //tv.setText(faces.size() + " Faces Seen");
                    }
                }
        ).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }
        );
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);


    }

    public void onClick(View view){

        if(view.getId() == R.id.startButton){
            tv.setText(fac.size() + " Faces Seen");

            FaceView overlay = (FaceView) findViewById(R.id.faceView);
            overlay.setContent(bitmap, fac);


            Bundle b = new Bundle();
            String text = msg;
            b.putString("LM", text);
            if(tts.handler != null){
                Message msg = tts.handler.obtainMessage(0);
                msg.setData(b);
                tts.handler.sendMessage(msg);
            }
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}