package com.mlab.mechanixlab.austimapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static android.R.attr.data;
import static android.R.attr.thumbnail;

public class MainActivity extends AppCompatActivity {

    Button ca, record;
    private static final int CAPTURE_REQUEST = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ca = (Button) findViewById(R.id.transport);
        record = (Button) findViewById(R.id.gallary);


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(MainActivity.this, GallaryActivity.class);
                startActivity(e);
            }
        });


        ca.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAPTURE_REQUEST);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  CAPTURE_REQUEST && resultCode == RESULT_OK && null != data) {
           /* Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            */
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent(this, SetImagewithrcordActivity.class);
            intent.putExtra("img",byteArray);
            startActivity(intent);
            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

}
