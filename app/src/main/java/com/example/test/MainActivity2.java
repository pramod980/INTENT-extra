package com.example.test;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private Button button2;
    private ImageView imageView2;
    private Button button3;
    ActivityResultLauncher<String>Open_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        imageView2=findViewById(R.id.imageView2);
        Open_camera=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageView2.setImageURI(result);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "opening gallery", Toast.LENGTH_SHORT).show();
                Open_camera.launch("image/*");
            }
        });
    }
    public void prasid(View view)
    {
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
        Log.d(TAG, "prasid: button work");
    }

}