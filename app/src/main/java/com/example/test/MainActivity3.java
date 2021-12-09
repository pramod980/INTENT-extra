package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private Button button4;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, "opening gallery", Toast.LENGTH_SHORT).show();
                if(ActivityCompat.checkSelfPermission(MainActivity3.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity3.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && requestCode==RESULT_OK)
        {
            imageView4=findViewById(R.id.imageView3);
            List<Bitmap> bitmaps= new ArrayList<>();
            ClipData clipData=data.getClipData();
            if(clipData!=null)
            {
                for(int i=0;i< clipData.getItemCount();i++)
                {
                    Uri imageUri= clipData.getItemAt(i).getUri();
                    try {
                        InputStream is=getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap= BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }
            else
            {
                Uri imageUri=data.getData();
                try {
                    InputStream is=getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap=BitmapFactory.decodeStream(is);
                    bitmaps.add(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for( final Bitmap b: bitmaps)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView4.setImageBitmap(b);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


                }
            }).start();



        }

    }
}