package com.example.test;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private  Button btn;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        btn=findViewById(R.id.btn);
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Bundle bundle=result.getData().getExtras();
                Bitmap bitmap=(Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "opening camera", Toast.LENGTH_SHORT).show();
                Intent open_camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(open_camera);

            }
        });

    }
    public void Next_page(View view)
    {
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }


}