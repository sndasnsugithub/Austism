package com.mlab.mechanixlab.austimapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.io.ByteArrayOutputStream;
import java.io.File;

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

        //SugarContext.init(this);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  CAPTURE_REQUEST && resultCode == RESULT_OK && null != data) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();*/

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = ImageHelper.getImageUri(getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(ImageHelper.getRealPathFromURI(tempUri,this));

            Intent intent = new Intent(this, SetImagewithrcordActivity.class);
            intent.putExtra("img",finalFile);
            startActivity(intent);



        }
    }

}
